package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CustomerView;
import actions.views.ReplyView;
import actions.views.VoiceView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.ReplyService;
import services.VoiceService;
/**
 * お客様の声に関する処理を行うActionクラス
 *
 */
public class ReplyAction extends ActionBase {

    private ReplyService service;
    private VoiceService voiceService;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new ReplyService();
        voiceService = new VoiceService();

        //メソッドを実行
        invoke();
        service.close();
        voiceService.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示する返信データを取得
        int page = getPage();
        List<ReplyView> replys = service.getAllPerPage(page);

        //全お客様の声データの件数を取得
        long replysCount = service.countAll();

        putRequestScope(AttributeConst.REPLY, replys); //取得した返信データ
        putRequestScope(AttributeConst.REP_COUNT, replysCount); //全ての返信データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_REP_INDEX);
    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

        //お客様の声への返信情報の空インスタンスに、返信の日付＝今日の日付を設定する
        ReplyView rv = new ReplyView();
        rv.setReplyDate(LocalDate.now());
        putRequestScope(AttributeConst.REPLY, rv); //日付のみ設定済みの返信インスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_REP_NEW);

    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //返信の日付が入力されていなければ、今日の日付を設定
            LocalDate day = null;
            if (getRequestParam(AttributeConst.REP_DATE) == null
                    || getRequestParam(AttributeConst.REP_DATE).equals("")) {
                day = LocalDate.now();
            } else {
                day = LocalDate.parse(getRequestParam(AttributeConst.REP_DATE));
            }

            //セッションからログイン中のお客様情報を取得
            CustomerView cv = (CustomerView) getSessionScope(AttributeConst.LOGIN_CUS);
            //ReplyView rv = voiceService.findOne(toNumber(getRequestParam(AttributeConst.VOI_ID)));

            //パラメータの値をもとに返信情報のインスタンスを作成する
            ReplyView rv = new ReplyView(
                    null,
                    voiceService.findOne(toNumber(getRequestParam(AttributeConst.VOI_ID))),
                    cv, //ログインしている従業員を、返信作成者として登録する
                    day,
                    getRequestParam(AttributeConst.REP_TITLE),
                    getRequestParam(AttributeConst.REP_CONTENT),
                    null);

            //返信情報登録
            List<String> errors = service.create(rv);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.REPLY, rv);//入力された返信情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_VOI_SHOW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_VOI, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件に返信データを取得する
        ReplyView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
        VoiceView vv = voiceService.findOne(toNumber(getRequestParam(AttributeConst.VOI_ID)));

        if (rv == null) {
            //該当の返信データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.REPLY, rv); //取得した返信データ
            putRequestScope(AttributeConst.VOICE, vv);

            //詳細画面を表示
            forward(ForwardConst.FW_VOI_SHOW);
        }
    }





    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    //public void edit() throws ServletException, IOException {

        //idを条件に声データを取得する
        //VoiceView vv = service.findOne(toNumber(getRequestParam(AttributeConst.VOI_ID)));

        //セッションからログイン中のお客様情報を取得
        //CustomerView cv = (CustomerView) getSessionScope(AttributeConst.LOGIN_CUS);

        //if (vv == null || cv.getId() != vv.getCustomer().getId()) {
            //該当の声データが存在しない、または
            //ログインしているお客様が声の作成者でない場合はエラー画面を表示
            //forward(ForwardConst.FW_ERR_UNKNOWN);

        //} else {

            //putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            //putRequestScope(AttributeConst.VOICE, vv); //取得した声データ

            //編集画面を表示
            //forward(ForwardConst.FW_VOI_EDIT);
        //}

    //}

    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    //public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        //if (checkToken()) {

            //idを条件に声データを取得する
            //VoiceView vv = service.findOne(toNumber(getRequestParam(AttributeConst.VOI_ID)));

            //入力された声内容を設定する
            //vv.setReportDate(toLocalDate(getRequestParam(AttributeConst.VOI_DATE)));
            //vv.setTitle(getRequestParam(AttributeConst.VOI_TITLE));
            //vv.setContent(getRequestParam(AttributeConst.VOI_CONTENT));

            //声データを更新する
            //List<String> errors = service.update(vv);

            //if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                //putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                //putRequestScope(AttributeConst.VOICE, vv); //入力された声情報
                //putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                //forward(ForwardConst.FW_VOI_EDIT);
            //} else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                //putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                //redirect(ForwardConst.ACT_VOI, ForwardConst.CMD_INDEX);

            //}
        //}
    //}

}
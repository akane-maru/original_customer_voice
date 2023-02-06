package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CustomerView;
import actions.views.VoiceView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.VoiceService;

/**
 * トップページに関する処理を行うActionクラス
 *
 */
public class TopAction extends ActionBase {

    private VoiceService service;

    /**
     * indexメソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new VoiceService();

        //メソッドを実行
        invoke();

        service.close();

    }

    /**
     * 一覧画面を表示する
     */
    public void index() throws ServletException, IOException {

        //セッションからログイン中のお客様情報を取得
        CustomerView loginCustomer = (CustomerView) getSessionScope(AttributeConst.LOGIN_CUS);

        //ログイン中のお客様が作成した声データを、指定されたページ数の一覧画面に表示する分取得する
        int page = getPage();
        List<VoiceView> voices = service.getMinePerPage(loginCustomer, page);


        //ログイン中のお客様が作成した声データの件数を取得
        long myVoicesCount = service.countAllMine(loginCustomer);

        putRequestScope(AttributeConst.VOICES, voices); //取得した声データ
        putRequestScope(AttributeConst.VOI_COUNT, myVoicesCount); //ログイン中のお客様が作成した声の数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }

}
package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CustomerView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.CustomerService;

/**
 * お客様に関わる処理を行うActionクラス
 *
 */
public class CustomerAction extends ActionBase {

    private CustomerService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new CustomerService();

        //メソッドを実行
        invoke();

        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //管理者かどうかのチェック
        if (checkAdmin()) {

        //指定されたページ数の一覧画面に表示するデータを取得
        int page = getPage();
        List<CustomerView> customers = service.getPerPage(page);

        //全てのお客様データの件数を取得
        long customerCount = service.countAll();

        putRequestScope(AttributeConst.CUSTOMERS, customers); //取得したお客様データ
        putRequestScope(AttributeConst.CUS_COUNT, customerCount); //全てのお客様データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_CUS_INDEX);

      }

    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException  {

        //管理者かどうかのチェック
        //if (checkAdmin()) {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.CUSTOMER, new CustomerView()); //空のお客様インスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_CUS_NEW);
      }

    //}

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        //if (checkAdmin() && checkToken()) {

            //パラメータの値を元にお客様情報のインスタンスを作成する
            CustomerView cv = new CustomerView(
                    null,
                    getRequestParam(AttributeConst.CUS_CODE),
                    getRequestParam(AttributeConst.CUS_NAME),
                    getRequestParam(AttributeConst.CUS_AGE),
                    getRequestParam(AttributeConst.CUS_GENDER),
                    getRequestParam(AttributeConst.CUS_PASS),
                    AttributeConst.ROLE_GENERAL.getIntegerValue(),
                    null,
                    null,
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //アプリケーションスコープからpepper文字列を取得
            String pepper = getContextScope(PropertyConst.PEPPER);

            //お客様情報登録
            List<String> errors = service.create(cv, pepper);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.CUSTOMER, cv); //入力されたお客様情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_CUS_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED_NEW.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_CUS, ForwardConst.CMD_INDEX);
            }

        }
    //}

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //管理者かどうかのチェック
        if (checkAdmin()) {

        //idを条件にお客様データを取得する
        CustomerView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CUS_ID)));

        if (cv == null || cv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

            //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }

        putRequestScope(AttributeConst.CUSTOMER, cv); //取得したお客様情報

        //詳細画面を表示
        forward(ForwardConst.FW_CUS_SHOW);
      }

    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //管理者かどうかのチェック
        if (checkAdmin()) {

        //idを条件にお客様データを取得する
        CustomerView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CUS_ID)));

        if (cv == null || cv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

            //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.CUSTOMER, cv); //取得したお客様情報

        //編集画面を表示する
        forward(ForwardConst.FW_CUS_EDIT);

      }

    }

    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkAdmin() && checkToken()) {
            //パラメータの値を元にお客様情報のインスタンスを作成する
            CustomerView cv = new CustomerView(
                    toNumber(getRequestParam(AttributeConst.CUS_ID)),
                    getRequestParam(AttributeConst.CUS_CODE),
                    getRequestParam(AttributeConst.CUS_NAME),
                    getRequestParam(AttributeConst.CUS_AGE),
                    getRequestParam(AttributeConst.CUS_GENDER),
                    getRequestParam(AttributeConst.CUS_PASS),
                    toNumber(getRequestParam(AttributeConst.CUS_ADMIN_FLG)),
                    null,
                    null,
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //アプリケーションスコープからpepper文字列を取得
            String pepper = getContextScope(PropertyConst.PEPPER);

            //お客様情報更新
            List<String> errors = service.update(cv, pepper);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.CUSTOMER, cv); //入力されたお客様情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_CUS_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());


              //管理者かどうかのチェック
                if (checkAdmin()) {

                //idを条件にお客様データを取得する
                CustomerView scv = service.findOne(toNumber(getRequestParam(AttributeConst.CUS_ID)));

                if (scv == null || scv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

                    //データが取得できなかった、または論理削除されている場合はエラー画面を表示
                    forward(ForwardConst.FW_ERR_UNKNOWN);
                    return;
                }

                putRequestScope(AttributeConst.CUSTOMER, scv); //取得したお客様情報


                //詳細画面を表示
                forward(ForwardConst.FW_CUS_SHOW);

                //一覧画面にリダイレクト
                //redirect(ForwardConst.ACT_CUS, ForwardConst.FW_CUS_SHOW);
                }
            }
        }
    }

    /**
     * 論理削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkAdmin() && checkToken()) {

            //idを条件にお客様データを論理削除する
            service.destroy(toNumber(getRequestParam(AttributeConst.CUS_ID)));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_CUS, ForwardConst.CMD_INDEX);
        }
    }

    /**
     * ログイン中のお客様が管理者かどうかチェックし、管理者でなければエラー画面を表示
     * true: 管理者 false: 管理者ではない
     * @throws ServletException
     * @throws IOException
     */
    private boolean checkAdmin() throws ServletException, IOException {

        //セッションからログイン中のお客様情報を取得
        CustomerView cv = (CustomerView) getSessionScope(AttributeConst.LOGIN_CUS);

        //管理者でなければエラー画面を表示
        if (cv.getAdminFlag() != AttributeConst.ROLE_ADMIN.getIntegerValue()) {

           forward(ForwardConst.FW_ERR_UNKNOWN);
            return false;

        } else {

            return true;
        }

    }

    /**
     * 削除画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void destroyConform() throws ServletException, IOException {

        //管理者かどうかのチェック
        if (checkAdmin()) {

        //idを条件にお客様データを取得する
        CustomerView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CUS_ID)));

        if (cv == null || cv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

            //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }

        putRequestScope(AttributeConst.CUSTOMER, cv); //取得したお客様情報

        //詳細画面を表示
        forward(ForwardConst.FW_DESTROY_CONFORM);
      }

    }


}
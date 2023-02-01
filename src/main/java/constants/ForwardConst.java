package constants;

/**
 * リクエストパラメーターの変数名、変数値、jspファイルの名前等画面遷移に関わる値を定義するEnumクラス
 *
 */
public enum ForwardConst {
    //action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_CUS("Customer"),
    ACT_VOI("Voice"),
    ACT_EMP("Employee"),
    ACT_AUTH("Auth"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),

    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_CUS_INDEX("customers/index"),
    FW_CUS_SHOW("customers/show"),
    FW_CUS_NEW("customers/new"),
    FW_CUS_EDIT("customers/edit"),
    FW_VOI_INDEX("voices/index"),
    FW_VOI_SHOW("voices/show"),
    FW_VOI_NEW("voices/new"),
    FW_VOI_EDIT("voices/edit"),
    FW_EMP_INDEX("employees/index"),
    FW_EMP_SHOW("employees/show"),
    FW_EMP_NEW("employees/new"),
    FW_EMP_EDIT("employees/edit");

    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getValue() {
        return this.text;
    }

}

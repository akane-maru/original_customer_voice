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

    ACT_VOI_REP("Reply"),

    ACT_REP("Reply"),
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
    CMD_DESTROY_CONFORM("destroyConform"),
    CMD_DESTROY("destroy"),
    CMD_REPLY("reply"),


    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_DESTROY_CONFORM("customers/delete"),
    FW_CUS_INDEX("customers/index"),
    FW_CUS_SHOW("customers/show"),
    FW_CUS_NEW("customers/new"),
    FW_CUS_EDIT("customers/edit"),
    FW_VOI_INDEX("voices/index"),
    FW_VOI_SHOW("voices/show"),
    FW_VOI_NEW("voices/new"),

    //FW_VOI_EDIT("voices/edit"),//編集機能は使用しない
    FW_REP_INDEX("replys/index"),
    FW_REP_SHOW("replys/show"),
    FW_REP_NEW("replys/new");

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

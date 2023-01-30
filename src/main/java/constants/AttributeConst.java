package constants;

/**
 * 画面の項目値等を定義するEnumクラス
 *
 */
public enum AttributeConst {
    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中のお客様
    LOGIN_EMP("login_customer"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //お客様管理
    CUSTOMER("customer"),
    CUSTOMERS("customers"),
    CUS_COUNT("customers_count"),
    CUS_ID("id"),
    CUS_CODE("code"),
    CUS_PASS("password"),
    CUS_NAME("name"),
    CUS_ADMIN_FLG("admin_flag"),

    //管理者フラグ
    ROLE_ADMIN(1),
    ROLE_GENERAL(0),

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //お客様の声管理
    VOICE("voice"),
    VOICES("voices"),
    VOI_COUNT("voices_count"),
    VOI_ID("id"),
    VOI_DATE("voice_date"),
    VOI_TITLE("title"),
    VOI_CONTENT("content_msg"),

    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

}

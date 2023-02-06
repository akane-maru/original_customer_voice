package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.CustomerView;
import constants.MessageConst;
import services.CustomerService;

/**
 * お客様インスタンスに設定されている値のバリデーションを行うクラス
 *
 */
public class CustomerValidator {
    /**
     * お客様インスタンスの各項目についてバリデーションを行う
     * @param service 呼び出し元Serviceクラスのインスタンス
     * @param cv CustomerViewのインスタンス
     * @param codeDuplicateCheckFlag お客様番号の重複チェックを実施するかどうか(実施する:true 実施しない:false)
     * @param passwordCheckFlag パスワードの入力チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーのリスト
     */
    public static List<String> validate(
            CustomerService service, CustomerView cv, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        //お客様番号のチェック
        String codeError = validateCode(service, cv.getCode(), codeDuplicateCheckFlag);
        if (!codeError.equals("")) {
            errors.add(codeError);
        }

        //氏名のチェック
        String nameError = validateName(cv.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }

        //年齢のチェック
        String ageError = validateAge(cv.getAge());
        if (!ageError.equals("")) {
            errors.add(ageError);
        }

        //性別のチェック
        String genderError = validateGender(cv.getGender());
        if (!genderError.equals("")) {
            errors.add(genderError);
        }

        //パスワードのチェック
        String passError = validatePassword(cv.getPassword(), passwordCheckFlag);
        if (!passError.equals("")) {
            errors.add(passError);
        }

        return errors;
    }

    /**
     * お客様番号の入力チェックを行い、エラーメッセージを返却
     * @param service CustomerServiceのインスタンス
     * @param code お客様番号
     * @param codeDuplicateCheckFlag お客様番号の重複チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーメッセージ
     */
    private static String validateCode(CustomerService service, String code, Boolean codeDuplicateCheckFlag) {

        //入力値がなければエラーメッセージを返却
        if (code == null || code.equals("")) {
            return MessageConst.E_NOEMP_CODE.getMessage();
        }

        if (codeDuplicateCheckFlag) {
            //社員番号の重複チェックを実施

            long employeesCount = isDuplicateCustomer(service, code);

            //同一社員番号が既に登録されている場合はエラーメッセージを返却
            if (employeesCount > 0) {
                return MessageConst.E_CUS_CODE_EXIST.getMessage();
            }
        }

        //エラーがない場合は空文字を返却
        return "";
    }

    /**
     * @param service CustomerServiceのインスタンス
     * @param code お客様番号
     * @return お客様テーブルに登録されている同一番号お客様のデータの件数
     */
    private static long isDuplicateCustomer(CustomerService service, String code) {

        long customersCount = service.countByCode(code);
        return customersCount;
    }

    /**
     * 氏名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 氏名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {

        if (name == null || name.equals("")) {
            return MessageConst.E_NONAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 年齢に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param age 年齢
     * @return エラーメッセージ
     */
    private static String validateAge(String age) {

        if (age == null || age.equals("")) {
            return MessageConst.E_NOAGE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 性別に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param gender 性別
     * @return エラーメッセージ
     */
    private static String validateGender(String gender) {

        if (gender == null || gender.equals("")) {
            return MessageConst.E_NOGENDER.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * パスワードの入力チェックを行い、エラーメッセージを返却
     * @param password パスワード
     * @param passwordCheckFlag パスワードの入力チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーメッセージ
     */
    private static String validatePassword(String password, Boolean passwordCheckFlag) {

        //入力チェックを実施 かつ 入力値がなければエラーメッセージを返却
        if (passwordCheckFlag && (password == null || password.equals(""))) {
            return MessageConst.E_NOPASSWORD.getMessage();
        }

        //エラーがない場合は空文字を返却
        return "";
    }

}

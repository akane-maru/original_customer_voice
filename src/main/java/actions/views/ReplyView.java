package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * お客様の声への返信情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class ReplyView {

    /**
     * id
     */
    private Integer id;

    /**
     * 返信を登録した従業員
     */
    private CustomerView customer;

    /**
     * いつの返信かを示す日付
     */
    private LocalDate replyDate;

    /**
     * 返信のタイトル
     */
    private String title;

    /**
     * 返信の内容
     */
    private String content;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    //private LocalDateTime updatedAt;
}

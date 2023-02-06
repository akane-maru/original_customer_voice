package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Voice;

/**
 * お客様の声データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class VoiceConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param vv VoiceViewのインスタンス
     * @return Voiceのインスタンス
     */
    public static Voice toModel(VoiceView vv) {
        return new Voice(
                vv.getId(),
                CustomerConverter.toModel(vv.getCustomer()),
                vv.getVoiceDate(),
                vv.getTitle(),
                vv.getContent(),
                vv.getCreatedAt());
                //vv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param v Voiceのインスタンス
     * @return VoiceViewのインスタンス
     */
    public static VoiceView toView(Voice v) {

        if (v == null) {
            return null;
        }

        return new VoiceView(
                v.getId(),
                CustomerConverter.toView(v.getCustomer()),
                v.getVoiceDate(),
                v.getTitle(),
                v.getContent(),
                v.getCreatedAt());
                //v.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<VoiceView> toViewList(List<Voice> list) {
        List<VoiceView> evs = new ArrayList<>();

        for (Voice v : list) {
            evs.add(toView(v));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param v DTOモデル(コピー先)
     * @param vv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Voice v, VoiceView vv) {
        v.setId(vv.getId());
        v.setCustomer(CustomerConverter.toModel(vv.getCustomer()));
        v.setVoiceDate(vv.getVoiceDate());
        v.setTitle(vv.getTitle());
        v.setContent(vv.getContent());
        v.setCreatedAt(vv.getCreatedAt());
        //v.setUpdatedAt(vv.getUpdatedAt());

    }

}
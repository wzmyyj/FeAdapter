package top.wzmyyj.diff_api.a;

import androidx.annotation.Nullable;

import java.util.Objects;

import top.wzmyyj.diff_api.IDiffModelType;
import top.wzmyyj.diff_api.Payload;

/**
 * Created on 2020/12/02.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
public class Diff$$Model$$YyModel<T extends YyModel> implements IDiffModelType<T> {

    public long id;

    @Nullable
    public String title;

    @Override
    public boolean isSameItem(T m) {
        return this.id == m.id;
    }

    @Override
    public boolean isSameContent(T m) {
        return Objects.equals(this.title, m.title);
    }

    @Override
    public void from(T m) {

    }

    @Override
    public Payload payload(T m) {
        return null;
    }

}
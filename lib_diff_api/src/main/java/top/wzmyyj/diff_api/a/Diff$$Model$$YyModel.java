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
public class Diff$$Model$$YyModel implements IDiffModelType {

    public long id;

    @Nullable
    public Object title;

    @Override
    public int sameItemCount() {
        return 1;
    }

    @Override
    public int sameContentCount() {
        return 1;
    }

    @Override
    public boolean isSameItem(Object o) {
        YyModel m = (YyModel) o;
        return this.id == m.id;
    }

    @Override
    public boolean isSameContent(Object o) {
        YyModel m = (YyModel) o;
        return Objects.equals(this.title, m.title);
    }

    @Override
    public void from(Object o) {
        YyModel m = (YyModel) o;

    }

    @Override
    public Payload payload(Object o) {
        YyModel m = (YyModel) o;
        return null;
    }

}
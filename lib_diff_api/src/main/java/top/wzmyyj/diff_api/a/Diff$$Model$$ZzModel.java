package top.wzmyyj.diff_api.a;

import android.os.Bundle;

/**
 * Created on 2020/12/02.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
public class Diff$$Model$$ZzModel<T extends ZzModel> extends Diff$$Model$$XxModel<T> {

    public boolean zzz;

    @Override
    public boolean isSameItem(T m) {
        return super.isSameItem(m);
    }

    @Override
    public boolean isSameContent(T m) {
        return super.isSameContent(m)
                && this.zzz == m.zzz;
    }

    @Override
    public void from(T m) {
        super.from(m);
        this.zzz = m.zzz;
    }


}

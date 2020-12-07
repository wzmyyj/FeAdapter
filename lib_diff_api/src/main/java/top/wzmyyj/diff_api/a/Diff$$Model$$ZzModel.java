package top.wzmyyj.diff_api.a;

import top.wzmyyj.diff_api.Payload;

/**
 * Created on 2020/12/02.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
public class Diff$$Model$$ZzModel extends Diff$$Model$$XxModel {

    public boolean zzz;

    @Override
    public int sameItemCount() {
        return super.sameItemCount();
    }

    @Override
    public int sameContentCount() {
        return 1 + super.sameContentCount();
    }

    @Override
    public boolean isSameItem(Object o) {
        ZzModel m = (ZzModel) o;
        return super.isSameItem(m);
    }

    @Override
    public boolean isSameContent(Object o) {
        ZzModel m = (ZzModel) o;
        return super.isSameContent(m)
                && this.zzz == m.zzz;
    }

    @Override
    public void from(Object o) {
        ZzModel m = (ZzModel) o;
        super.from(m);
        this.zzz = m.zzz;
    }

    @Override
    public Payload payload(Object o) {
        ZzModel m = (ZzModel) o;
        Payload p = super.payload(m);
        if (p == null) p = new Payload();

        return p;
    }
}

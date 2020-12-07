package top.wzmyyj.diff_api.a;

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
public class Diff$$Model$$XxModel implements IDiffModelType {

    private long id;

    private int count;

    private boolean valid;

    private Object name;

    private final Diff$$Model$$YyModel yy = new Diff$$Model$$YyModel();

    @Override
    public int sameItemCount() {
        return 1 + yy.sameItemCount();
    }

    @Override
    public int sameContentCount() {
        return 3 + yy.sameContentCount();
    }

    @Override
    public boolean isSameItem(Object o) {
        XxModel m = (XxModel) o;
        return this.id == m.id
                && this.yy.isSameItem(m.yy);
    }

    @Override
    public boolean isSameContent(Object o) {
        XxModel m = (XxModel) o;
        return this.count == m.count && this.valid == m.valid
                && Objects.equals(this.name, m.name)
                && this.yy.isSameContent(m.yy);
    }

    @Override
    public void from(Object o) {
        XxModel m = (XxModel) o;
        this.id = m.id;
        this.count = m.count;
        this.valid = m.valid;
        this.name = m.name;
        this.yy.from(m.yy);
    }

    @Override
    public Payload payload(Object o) {
        XxModel m = (XxModel) o;
        Payload p = new Payload();
        if (this.count != m.count) {
            p.change.put("count", true);
            p.data.put("count", m.count);
        }
        if (this.valid != m.valid) {
            p.change.put("valid", true);
            p.data.put("valid", m.valid);
        }
        if (!Objects.equals(this.name, m.name)) {
            p.change.put("name", true);
            p.data.put("name", m.name);
        }
        if (!this.yy.isSameContent(m.yy)) {
            p.change.put("yy", true);
            p.data.put("yy", this.yy.payload(m.yy));
        }
        return p;
    }
}

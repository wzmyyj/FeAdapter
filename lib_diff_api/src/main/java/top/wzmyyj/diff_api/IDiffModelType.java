package top.wzmyyj.diff_api;

/**
 * Created on 2020/12/02.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IDiffModelType<T> {

     boolean isSameItem(T m);

    boolean isSameContent(T m);

    void from(T m);

    Payload payload(T m);
}

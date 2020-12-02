package top.wzmyyj.diff_api.a;

import top.wzmyyj.diff_annotation.SameContent;
import top.wzmyyj.diff_annotation.SameItem;

/**
 * Created on 2020/12/02.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
public class XxModel {

    @SameItem
    public long id;

    @SameContent
    public String name;

    @SameContent
    public int count;

    @SameContent
    public boolean valid;

    @SameItem
    @SameContent
    public YyModel yy;

}

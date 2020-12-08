package top.wzmyyj.diff_api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

/**
 * Created on 2020/12/08.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DiffModelHelper {

    private final Map<Object, IDiffModelType> modelMap = new WeakHashMap<>();
    private final Map<Object, IDiffModelType> bindMap = new WeakHashMap<>();

    private boolean byObjectsEquals = true;

    public void isSameItemByObjectsEquals(boolean use) {
        this.byObjectsEquals = use;
    }

    public boolean isSameItem(@NonNull Object oldModel, @NonNull Object newModel) {
        IDiffModelType diff = modelMap.get(oldModel);
        if (diff == null) return false;
        if (diff.canHandle(newModel) && diff.sameItemCount() > 0) {
            return diff.isSameItem(newModel);
        }
        return byObjectsEquals && Objects.equals(oldModel, newModel);
    }

    public boolean isSameContent(@NonNull Object oldModel, @NonNull Object newModel) {
        IDiffModelType diff = modelMap.get(oldModel);
        if (diff == null) return false;
        if (diff.canHandle(newModel) && diff.sameContentCount() > 0) {
            return diff.isSameContent(newModel);
        }
        return false;
    }

    @Nullable
    public Payload getPayload(@NonNull Object oldModel, @NonNull Object newModel) {
        IDiffModelType diff = modelMap.get(oldModel);
        if (diff == null) return null;
        if (diff.canHandle(newModel) && diff.sameContentCount() > 0) {
            return diff.payload(newModel);
        }
        return null;
    }

    public void bindNewData(@NonNull Object bindObj, @NonNull Object newModel) {
        IDiffModelType oldDiff = bindMap.get(bindObj);
        if (oldDiff != null && oldDiff.canHandle(newModel)) {
            oldDiff.from(newModel);
            return;
        }
        IDiffModelType diff = getDiff(newModel);
        if (diff == null) return;
        diff.from(newModel);
        modelMap.put(newModel, diff);
        bindMap.put(bindObj, diff);
    }

    private IDiffModelType getDiff(@NonNull Object model) {
        IDiffModelFactory factory = DiffModelFactoryManager.getInstance().getFactory(model);
        if (factory != null) return factory.create();
        return null;
    }

}

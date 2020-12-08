package top.wzmyyj.diff_api;

import android.util.LruCache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020/12/08.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DiffModelFactoryManager {

    private static DiffModelFactoryManager instance = null;

    public static DiffModelFactoryManager getInstance() {
        if (instance == null) {
            synchronized (DiffModelFactoryManager.class) {
                if (instance == null) {
                    instance = new DiffModelFactoryManager();
                }
            }
        }
        return instance;
    }

    private DiffModelFactoryManager() {
    }

    public static final String DIFF_API_PACKAGE = "top.wzmyyj.diff_api";
    public static final String FACTORY_HELPER_NAME = "Diff$$Create$$FactoryHelperImpl";

    public static final int MAX_FACTORY_CACHE_SIZE = 50;

    private final LruCache<Class<?>, IDiffModelFactory> cache = new LruCache<>(MAX_FACTORY_CACHE_SIZE);
    private final Map<Class<?>, Class<?>> clazzMap = new HashMap<>();

    private IDiffCreateFactoryHelper factoryHelper = null;

    @Nullable
    public IDiffModelFactory getFactory(@NonNull Object o) {
        Class<?> clazz = clazzMap.get(o.getClass());
        if (clazz != null) {
            IDiffModelFactory factory = cache.get(clazz);
            if (factory != null) {
                return factory;
            }
        }
        if (factoryHelper == null) {
            try {
                Class<?> aClass = Class.forName(DIFF_API_PACKAGE + FACTORY_HELPER_NAME);
                factoryHelper = (IDiffCreateFactoryHelper) aClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (factoryHelper != null) {
            IDiffModelFactory factory = factoryHelper.createFactory(o);
            clazzMap.put(o.getClass(), factory.getClass());
            cache.put(factory.getClass(), factory);
            return factory;
        }
        return null;
    }

}

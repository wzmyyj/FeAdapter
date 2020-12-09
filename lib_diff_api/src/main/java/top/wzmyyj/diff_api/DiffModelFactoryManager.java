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

    // 由于model种类不确定，可能很多，IDiffModelFactory时间复杂度不确定，因此做缓存工厂
    private final LruCache<Class<?>, IDiffModelFactory> cache = new LruCache<>(MAX_FACTORY_CACHE_SIZE);
    // model子类和父类可能用的是同一个工厂
    private final Map<Class<?>, Class<?>> clazzMap = new HashMap<>();

    // 正真创建工厂的对象
    private IDiffCreateFactoryHelper factoryHelper = null;

    /**
     * 获取工厂。
     *
     * @param model model对象
     * @return 工厂
     */
    @Nullable
    public IDiffModelFactory getFactory(@NonNull Object model) {
        Class<?> clazz = clazzMap.get(model.getClass());
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
            IDiffModelFactory factory = factoryHelper.createFactory(model);
            clazzMap.put(model.getClass(), factory.getClass());
            cache.put(factory.getClass(), factory);
            return factory;
        }
        return null;
    }

}

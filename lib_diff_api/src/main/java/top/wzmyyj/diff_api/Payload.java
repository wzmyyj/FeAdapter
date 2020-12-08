package top.wzmyyj.diff_api;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020/12/02.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
public class Payload {

    public Map<String, Boolean> change = new HashMap<>();

    public Map<String, Object> data = new HashMap<>();

    public boolean isEmpty() {
        return change.isEmpty();
    }

    public boolean isChange(@NonNull String key) {
        return change.get(key);
    }
}

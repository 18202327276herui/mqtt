package usung.com.mqttclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import usung.com.mqttclient.bean.user.UserAccountAndHandGesturePwd;

import static android.content.Context.MODE_PRIVATE;

/**
 * 新添加的方法对SharedPreference的使用做了建议的封装，对外公布出put，get，remove，clear等等方法；
 * 注意一点，里面所有的commit操作使用了SharedPreferencesCompat.apply进行了替代，目的是尽可能的使用apply代替commit
 * 因为commit方法是同步的，并且我们很多时候的commit操作都是UI线程中，毕竟是IO操作，尽可能异步； 所以我们使用apply进行替代，apply异步的进行写入；
 * 但是apply相当于commit来说是new API呢，为了更好的兼容，我们做了适配； SharedPreferencesCompat也可以给大家创建兼容类提供了一定的参考~~
 */
public class SharePreferenceUtil {
    public Context mContext;
    public String spKey, spValue;
    public SharedPreferences sp;
    public Editor spEditor;

    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "smart_trade_ky";

    public SharePreferenceUtil(Context mContext) {
        super();
        this.mContext = mContext;
    }

    //存
    public void savePreference(String spName, String spKey, String spValue) {
        spEditor = mContext.getSharedPreferences(spName, 0).edit();
        spEditor.putString(spKey, spValue);
        spEditor.commit();
    }

    //取
    public String getPreference(String spName, String spKey) {
        sp = mContext.getSharedPreferences(spName, 0);
        String value = "";
        if (sp != null) {
            value = sp.getString(spKey, "");
        }
        return value;
    }

    //删
    public void removePreference(String spName, String spKey) {
        sp = mContext.getSharedPreferences(spName, 0);
        if (sp != null) {
            sp.edit().remove(spKey).commit();
        }
    }

    @Override
    public String toString() {
        return "SharePreferenceUtil [mContext=" + mContext + ", spKey=" + spKey
                + ", spValue=" + spValue + "]";
    }

    /**
     * 利用 sharePreference 存值
     *
     * @param spValue 保存数据示例
     *                new SharedPreferencesUtil(context).savePreference(spName, spKey, 0);                                                                                        SharedPreferencesUtils.getParam(TimerActivity.this, "int", 0);
     */
    public void savePreference(String spName, String spKey, Object spValue) {
        spEditor = mContext.getSharedPreferences(spName, MODE_PRIVATE).edit();
        String type = spValue.getClass().getSimpleName();
        if ("String".equals(type)) {
            spEditor.putString(spKey, (String) spValue);
        } else if ("Integer".equals(type)) {
            spEditor.putInt(spKey, (Integer) spValue);
        } else if ("Boolean".equals(type)) {
            spEditor.putBoolean(spKey, (Boolean) spValue);
        } else if ("Float".equals(type)) {
            spEditor.putFloat(spKey, (Float) spValue);
        } else if ("Long".equals(type)) {
            spEditor.putLong(spKey, (Long) spValue);
        }
        spEditor.commit();
    }

    /**
     * 利用 sharePreference 取值
     *
     * @return 取数据示例
     * int i = new SharedPreferencesUtil(context).getPreferenceValue(spName, spKey, 0);                                                                                        SharedPreferencesUtils.getParam(TimerActivity.this, "int", 0);
     */
    @SuppressWarnings("unchecked")
    public <T> T getPreferenceValue(String spName, String spKey, T t) {
        sp = mContext.getSharedPreferences(spName, MODE_PRIVATE);
        if (sp != null) {
            if (t instanceof String) {
                return (T) sp.getString(spKey, (String) t);
            } else if (t instanceof Integer) {
                return (T) Integer.valueOf(sp.getInt(spKey, (Integer) t));
            } else if (t instanceof Boolean) {
                return (T) Boolean.valueOf(sp.getBoolean(spKey, (Boolean) t));
            } else if (t instanceof Float) {
                return (T) Float.valueOf(sp.getFloat(spKey, (Float) t));
            } else if (t instanceof Long) {
                return (T) Long.valueOf(sp.getLong(spKey, (Long) t));
            }
        }
        return t;
    }

    /**
     * 利用 sharePreference 取值
     *
     * @param mContext
     * @param spName
     * @param spKey
     * @return 取数据示例
     * 1     SharedPreferencesUtils.getParam(TimerActivity.this, "String", "");                                                                                        SharedPreferencesUtils.getParam(TimerActivity.this, "int", 0);
     * 2     SharedPreferencesUtils.getParam(TimerActivity.this, "boolean", false);
     * 3     SharedPreferencesUtils.getParam(TimerActivity.this, "long", 0L);
     * 4     SharedPreferencesUtils.getParam(TimerActivity.this, "float", 0.0f);
     */
    public static Object getPreferenceValue(Context mContext, String spName, String spKey, Object defaultObject) {
        SharedPreferences sp = mContext.getSharedPreferences(spName, MODE_PRIVATE);
        String type = defaultObject.getClass().getSimpleName();
        String spValue = "";
        if (sp != null) {
            if ("String".equals(type)) {
                return sp.getString(spKey, (String) defaultObject);
            } else if ("Integer".equals(type)) {
                return sp.getInt(spKey, (Integer) defaultObject);
            } else if ("Boolean".equals(type)) {
                return sp.getBoolean(spKey, (Boolean) defaultObject);
            } else if ("Float".equals(type)) {
                return sp.getFloat(spKey, (Float) defaultObject);
            } else if ("Long".equals(type)) {
                return sp.getLong(spKey, (Long) defaultObject);
            }
        }
        return spValue;
    }

	/*public <T> static void saveList(Context context, List<T> list){
        SharedPreferences sharedPreferences = context.getSharedPreferences(APPConstants.HAND_GESTURE_PAW, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.
		editor.apply();

		// 把集合转化为json数据，然后在保存

	}*/

    /**
     * 使用share保存List
     *
     * @param spKey
     * @param datalist
     */
    public void setDataList(Context context, String spName, String spKey, List<UserAccountAndHandGesturePwd> datalist) {
        if (null == datalist/* || datalist.size() <= 0*/) {
            return;
        }
        // 把集合转化为json数据，然后在保存
        Gson gson = new Gson();
        String strJson = gson.toJson(datalist);
        SharedPreferences sharedPreferences = context.getSharedPreferences(spName, MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
//        editor.clear();
        editor.putString(spKey, strJson);
        editor.apply();
    }

    /**
     * 使用share获取List
     *
     * @param spKey
     * @return
     */
    public ArrayList<UserAccountAndHandGesturePwd> getDataList(Context context, String spName, String spKey) {
        ArrayList<UserAccountAndHandGesturePwd> datalist = new ArrayList<UserAccountAndHandGesturePwd>();
        SharedPreferences sharedPreferences = context.getSharedPreferences(spName, MODE_PRIVATE);
        String strJson = sharedPreferences.getString(spKey, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<UserAccountAndHandGesturePwd>>() {
        }.getType());
        return datalist;
    }

    /******************************从此处开始为新增的方法*****************************************/

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    @SuppressWarnings("unchecked")
    public static void put(Context context, String key, Object object) {

        if (object != null) {
            SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
            Editor editor = sp.edit();

            if (object instanceof String) {
                editor.putString(key, (String) object);
            } else if (object instanceof Integer) {
                editor.putInt(key, (Integer) object);
            } else if (object instanceof Boolean) {
                editor.putBoolean(key, (Boolean) object);
            } else if (object instanceof Float) {
                editor.putFloat(key, (Float) object);
            } else if (object instanceof Long) {
                editor.putLong(key, (Long) object);
            } else if (object instanceof Set<?>) {
                editor.putStringSet(key, (Set<String>) object);
            } else {
                editor.putString(key, object.toString());
            }
            SharedPreferencesCompat.apply(editor);
        }
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    public static Boolean getBoolean(Context context, String key, Boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static Integer getInt(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static Float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getFloat(key, defaultValue);
    }

    public static Long getLong(Context context, String key, long defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, "");
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }
}
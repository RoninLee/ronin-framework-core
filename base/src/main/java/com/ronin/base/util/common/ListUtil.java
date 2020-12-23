package com.ronin.base.util.common;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 关于List的工具集合.
 * <p>
 * 1. 常用函数(如是否为空，sort/binarySearch/shuffle/reverse(via JDK Collection)
 * <p>
 * 2. 各种构造函数(from guava and JDK Collection)
 * <p>
 * 3. 各种扩展List类型的创建函数
 * <p>
 * 5. 集合运算：交集，并集, 差集, 补集，from Commons Collection，但对其不合理的地方做了修正
 */
@Slf4j
public class ListUtil {

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(List<?> list) {
        return (list == null) || list.isEmpty();
    }

    /**
     * 判断是否不为空.
     */
    public static boolean isNotEmpty(List<?> list) {
        return (list != null) && !(list.isEmpty());
    }

    /**
     * 获取第一个元素, 如果List为空返回 null.
     */
    public static <T> T getFirst(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 获取最后一个元素，如果List为空返回null.
     */
    public static <T> T getLast(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }

        return list.get(list.size() - 1);
    }

    ///////////////// from Guava的构造函数///////////////////

    /**
     * 根据等号左边的类型，构造类型正确的ArrayList.
     *
     * @deprecated JDK7开始已经简化
     */
    @Deprecated
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<T>();
    }

    /**
     * 根据等号左边的类型，构造类型正确的ArrayList, 并初始化元素.
     *
     * @see Lists#newArrayList(Object...)
     */
    public static <T> ArrayList<T> newArrayList(T... elements) {
        return Lists.newArrayList(elements);
    }

    /**
     * 根据等号左边的类型，构造类型正确的ArrayList, 并初始化元素.
     *
     * @see Lists#newArrayList(Iterable)
     */
    public static <T> ArrayList<T> newArrayList(Iterable<T> elements) {
        return Lists.newArrayList(elements);
    }

    /**
     * 根据等号左边的类型，构造类型正确的ArrayList, 并初始化数组大小.
     *
     * @see Lists#newArrayListWithCapacity(int)
     */
    public static <T> ArrayList<T> newArrayListWithCapacity(int initSize) {
        return new ArrayList<T>(initSize);
    }

    /**
     * 根据等号左边的类型，构造类型正确的LinkedList.
     *
     * @deprecated JDK7开始已经简化
     */
    @Deprecated
    public static <T> LinkedList<T> newLinkedList() {
        return new LinkedList<T>();
    }

    /**
     * 根据等号左边的类型，构造类型正确的LinkedList.
     *
     * @see Lists#newLinkedList()
     */
    public static <T> LinkedList<T> newLinkedList(Iterable<? extends T> elements) {
        return Lists.newLinkedList(elements);
    }

    /**
     * 根据等号左边的类型，构造类型正确的CopyOnWriteArrayList.
     *
     * @deprecated JDK7开始已经简化
     */
    @Deprecated
    public static <T> CopyOnWriteArrayList<T> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<T>();
    }

    /**
     * 根据等号左边的类型，构造类型转换的CopyOnWriteArrayList, 并初始化元素.
     */
    public static <T> CopyOnWriteArrayList<T> newCopyOnWriteArrayList(T... elements) {
        return new CopyOnWriteArrayList<T>(elements);
    }

    ///////////////// from JDK Collections的常用构造函数 ///////////////////

    /**
     * 返回一个空的结构特殊的List，节约空间.
     * <p>
     * 注意返回的List不可写, 写入会抛出UnsupportedOperationException.
     *
     * @see Collections#emptyList()
     */
    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    /**
     * 如果list为null，转化为一个安全的空List.
     * <p>
     * 注意返回的List不可写, 写入会抛出UnsupportedOperationException.
     *
     * @see Collections#emptyList()
     */
    public static <T> List<T> emptyListIfNull(final List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    /**
     * 返回只含一个元素但结构特殊的List，节约空间.
     * <p>
     * 注意返回的List不可写, 写入会抛出UnsupportedOperationException.
     *
     * @see Collections#singletonList(Object)
     */
    public static <T> List<T> singletonList(T o) {
        return Collections.singletonList(o);
    }

    /**
     * 返回包装后不可修改的List.
     * <p>
     * 如果尝试写入会抛出UnsupportedOperationException.
     *
     * @see Collections#unmodifiableList(List)
     */
    public static <T> List<T> unmodifiableList(List<? extends T> list) {
        return Collections.unmodifiableList(list);
    }

    /**
     * 返回包装后同步的List，所有方法都会被synchronized原语同步.
     * <p>
     * 用于CopyOnWriteArrayList与 ArrayDequeue均不符合的场景
     */
    public static <T> List<T> synchronizedList(List<T> list) {
        return Collections.synchronizedList(list);
    }

    ///////////////// from JDK Collections的常用函数 ///////////////////

    /**
     * 升序排序, 采用JDK认为最优的排序算法, 使用元素自身的compareTo()方法
     *
     * @see Collections#sort(List)
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        Collections.sort(list);
    }

    /**
     * 倒序排序, 采用JDK认为最优的排序算法,使用元素自身的compareTo()方法
     *
     * @see Collections#sort(List)
     */
    public static <T extends Comparable<? super T>> void sortReverse(List<T> list) {
        list.sort(Collections.reverseOrder());
    }

    /**
     * 升序排序, 采用JDK认为最优的排序算法, 使用Comparetor.
     *
     * @see Collections#sort(List, Comparator)
     */
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
    }

    /**
     * 倒序排序, 采用JDK认为最优的排序算法, 使用Comparator
     *
     * @see Collections#sort(List, Comparator)
     */
    public static <T> void sortReverse(List<T> list, Comparator<? super T> c) {
        list.sort(Collections.reverseOrder(c));
    }

    /**
     * 二分法快速查找对象, 使用Comparable对象自身的比较.
     * <p>
     * list必须已按升序排序.
     * <p>
     * 如果不存在，返回一个负数，代表如果要插入这个对象，应该插入的位置
     *
     * @see Collections#binarySearch(List, Object)
     */
    public static <T> int binarySearch(List<? extends Comparable<? super T>> sortedList, T key) {
        return Collections.binarySearch(sortedList, key);
    }

    /**
     * 二分法快速查找对象，使用Comparator.
     * <p>
     * list必须已按升序排序.
     * <p>
     * 如果不存在，返回一个负数，代表如果要插入这个对象，应该插入的位置
     *
     * @see Collections#binarySearch(List, Object, Comparator)
     */
    public static <T> int binarySearch(List<? extends T> sortedList, T key, Comparator<? super T> c) {
        return Collections.binarySearch(sortedList, key, c);
    }

    /**
     * 随机乱序，使用默认的Random.
     *
     * @see Collections#shuffle(List)
     */
    public static void shuffle(List<?> list) {
        Collections.shuffle(list);
    }

    /**
     * 随机乱序，使用传入的Random.
     *
     * @see Collections#shuffle(List, Random)
     */
    public static void shuffle(List<?> list, Random rnd) {
        Collections.shuffle(list, rnd);
    }

    /**
     * 返回一个倒转顺序访问的List，仅仅是一个倒序的View，不会实际多生成一个List
     *
     * @see Lists#reverse(List)
     */
    public static <T> List<T> reverse(final List<T> list) {
        return Lists.reverse(list);
    }
    ///////////////// from guava的函数 ///////////////////

    /**
     * List分页函数
     */
    public static <T> List<List<T>> partition(List<T> list, int size) {
        return Lists.partition(list, size);
    }

    ///////////////// 其他处理函数 ///////////////

    /**
     * 清理掉List中的Null对象
     */
    public static <T> void notNullList(List<T> list) {
        if (isEmpty(list)) {
            return;
        }
        // 清理掉null的集合
        list.removeIf(Objects::isNull);
    }

    public static <T> void uniqueNotNullList(List<T> list) {
        if (isEmpty(list)) {
            return;
        }
        Iterator<T> ite = list.iterator();
        Set<T> set = new HashSet<>((int) (list.size() / 0.75F + 1.0F));

        while (ite.hasNext()) {
            T obj = ite.next();
            // 清理掉null的集合
            if (null == obj) {
                ite.remove();
                continue;
            }
            // 清理掉重复的集合
            if (set.contains(obj)) {
                ite.remove();
                continue;
            }
            set.add(obj);
        }
    }

    ///////////////// 集合运算 ///////////////////

    /**
     * list1,list2的并集（在list1或list2中的对象），产生新List
     * <p>
     * 对比Apache Common Collection4 ListUtils, 优化了初始大小
     */
    public static <E> List<E> union(final List<? extends E> list1, final List<? extends E> list2) {
        final List<E> result = new ArrayList<E>(list1.size() + list2.size());
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }

    /**
     * list1, list2的交集（同时在list1和list2的对象），产生新List
     * <p>
     * copy from Apache Common Collection4 ListUtils，但其做了不合理的去重，因此重新改为性能较低但不去重的版本
     * <p>
     * 与List.retainAll()相比，考虑了的List中相同元素出现的次数,
     * 如"a"在list1出现两次，而在list2中只出现一次，则交集里会保留一个"a".
     */
    public static <T> List<T> intersection(final List<? extends T> list1, final List<? extends T> list2) {
        List<? extends T> smaller = list1;
        List<? extends T> larger = list2;
        if (list1.size() > list2.size()) {
            smaller = list2;
            larger = list1;
        }

        // 克隆一个可修改的副本
        List<T> newSmaller = new ArrayList<T>(smaller);
        List<T> result = new ArrayList<T>(smaller.size());
        for (final T e : larger) {
            if (newSmaller.contains(e)) {
                result.add(e);
                newSmaller.remove(e);
            }
        }
        return result;
    }

    /**
     * list1, list2的差集（在list1，不在list2中的对象），产生新List.
     * <p>
     * 与List.removeAll()相比，会计算元素出现的次数，如"a"在list1出现两次，而在list2中只出现一次，则差集里会保留一个"a".
     */
    public static <T> List<T> difference(final List<? extends T> list1, final List<? extends T> list2) {
        final List<T> result = new ArrayList<T>(list1);

        for (T t : list2) {
            result.remove(t);
        }

        return result;
    }

    /**
     * list1, list2的补集（在list1或list2中，但不在交集中的对象，又叫反交集）产生新List.
     * <p>
     * copy from Apache Common Collection4 ListUtils，但其并集－交集时，初始大小没有对交集*2，所以做了修改
     */
    public static <T> List<T> disjoint(final List<? extends T> list1, final List<? extends T> list2) {
        List<T> intersection = intersection(list1, list2);
        List<T> towIntersection = union(intersection, intersection);
        return difference(union(list1, list2), towIntersection);
    }

    /**
     * <p>
     * Discription:[将一个list均分成n个list,主要通过偏移量来实现的 ]
     * </p>
     *
     * @param source source
     * @param n      n
     * @return java.util.List
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n; // (先计算出余数)
        int number = source.size() / n; // 然后是商
        int offset = 0;// 偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    /**
     * <p>
     * Discription:[将一个list按照指定的大小拆分]
     * </p>
     *
     * @param list 要拆分的集合
     * @param size 指定的大小
     * @return java.util.List
     */
    public static <T> List<List<T>> getSplitList(List<T> list, int size) {
        List<List<T>> returnList = new ArrayList<List<T>>();
        int listSize = list.size();
        int num = listSize % size == 0 ? listSize / size : (listSize / size + 1);
        int start = 0;
        int end = 0;
        for (int i = 1; i <= num; i++) {
            start = (i - 1) * size;
            end = Math.min(i * size, listSize);
            returnList.add(list.subList(start, end));
        }
        return returnList;
    }

    /**
     * <p>
     * Discription:[获取list元素的某个字段值，组成数组返回]
     * </p>
     *
     * @param list   list
     * @param column column
     * @return String[]
     */
    public static <T> String[] toArray(List<T> list, String column) {
        try {
            if (list == null || list.isEmpty()) {
                return new String[0];
            }
            if (StringUtils.isEmpty(column)) {
                return null;
            }
            int len = list.size();
            List<String> result = new ArrayList<>(len);
            String methodName = "get" + column.substring(0, 1).toUpperCase() + column.substring(1);
            for (T t : list) {
                Method method = t.getClass().getMethod(methodName);
                Object obj = method.invoke(t);
                if (obj != null) {
                    result.add(String.valueOf(obj));
                }
            }
            return result.toArray(new String[0]);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            log.error("com.ronin.base.util.common.ListUtil.toArray(java.util.List<T>, java.lang.String):{}", e.getMessage());
        }
        return new String[0];
    }

    public static String[] toArray(List<String> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    /**
     * splitAry方法<br>
     *
     * @param ary     要分割的数组
     * @param subSize 分割的块大小
     * @return Object[]
     */
    private static Object[] splitAry(int[] ary, int subSize) {
        int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;
        List<List<Integer>> subAryList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int index = i * subSize;
            List<Integer> list = new ArrayList<>();
            int j = 0;
            while (j < subSize && index < ary.length) {
                list.add(ary[index++]);
                j++;
            }
            subAryList.add(list);
        }
        Object[] subAry = new Object[subAryList.size()];
        for (int i = 0; i < subAryList.size(); i++) {
            List<Integer> subList = subAryList.get(i);
            int[] subAryItem = new int[subList.size()];
            for (int j = 0; j < subList.size(); j++) {
                subAryItem[j] = subList.get(j);
            }
            subAry[i] = subAryItem;
        }
        return subAry;
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }
        Object obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            log.error("com.ronin.base.util.common.ListUtil.objectToMap:{}", e.getMessage());
        }
        return map;
    }

    /**
     * <p>Description:[String数据转Long数组]</p>
     *
     * @param stringArray String数组
     * @return LONG数组
     */
    public static Long[] StringArray2LongArray(String[] stringArray) {
        List<Long> list = new ArrayList<>();
        for (String str : stringArray) {
            try {
                list.add(Long.parseLong(str));
            } catch (NumberFormatException e) {
                log.error("com.ronin.base.util.common.ListUtil.StringArray2LongArray:{}", e.getMessage());
            }
        }
        return list.toArray(new Long[0]);
    }

    /**
     * 分页
     */
    public static <S> List<S> listLimit(List<S> list, Integer pageNo, Integer pageSize) {
        List<S> list2 = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return list2;
        }
        int totalCount = list.size();
        pageNo = pageNo - 1;
        int fromIndex = pageNo * pageSize;
        if (fromIndex >= totalCount) {
            return list2;
        }
        int toIndex = ((pageNo + 1) * pageSize);
        if (toIndex > totalCount) {
            toIndex = totalCount;
        }
        list2 = list.subList(fromIndex, toIndex);
        return list2;
    }

    /**
     * 返回左侧有右侧无的数据（注意：不去重）
     */
    public static <E> List<E> leftDiff(List<E> left, List<E> right) {
        if (isEmpty(left) || isEmpty(right)) {
            return left;
        }
        List<E> leftDiff = new ArrayList<E>();
        for (E l : left) {
            if (l == null) {
                continue;
            }
            boolean exists = false;
            for (E r : right) {
                if (l.equals(r)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                leftDiff.add(l);
            }
        }
        return leftDiff;
    }

    /**
     * 获取最后一个元素
     *
     * @return 最后一个元素
     */
    public static <E> E last(List<E> list) {
        if (isEmpty(list)) {
            return null;
        }
        return list.get(list.size() - 1);
    }
}

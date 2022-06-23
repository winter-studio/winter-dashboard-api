package org.winterframework.dashboard.data.mybatis;

import cn.hutool.core.collection.CollectionUtil;

import java.util.Stack;

public class DataModifyPermissionHolder {
    private static final ThreadLocal<Stack<String>> holder = new ThreadLocal<>();

    public static void set(String column) {
        Stack<String> columns = holder.get();
        if (columns == null) {
            columns = new Stack<>();
            holder.set(columns);
        }
        columns.push(column);
        holder.set(columns);
    }

    public static void clear() {
        Stack<String> columns = holder.get();
        if (columns != null) {
            if (!columns.isEmpty()) {
                columns.pop();
            }

            if (columns.isEmpty()) {
                holder.remove();
            }
        }
    }

    public static String get(){
        Stack<String> columns = holder.get();
        if (columns != null && !columns.isEmpty()) {
            return columns.peek();
        }
        return null;
    }

    public static boolean exists(){
        return CollectionUtil.isNotEmpty(holder.get());
    }
}

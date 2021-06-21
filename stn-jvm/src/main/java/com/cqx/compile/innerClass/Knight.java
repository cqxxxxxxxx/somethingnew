package com.cqx.compile.innerClass;

/**
 * Created by BG307435 on 2018/8/28.
 */
public class Knight {
    final Object object = new Object();

    private MyInterface myInterface = new MyInterface() {
        @Override
        public void onTaskClick() {
            System.out.println(object);
        }
    };

    public interface MyInterface {
        void onTaskClick();
    }

    private class Knife {
        private String damage = "99";

        private String stab() {
            return damage;
        }
    }
}

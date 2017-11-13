## **AdvertSwitcher**

![](imgs/广告滚动.gif)

#### 配置使用

1. 工程跟目录下的build.gradle

   ```groovy
   allprojects {
   	repositories {
   		...
   		maven { url 'https://jitpack.io' }
   	}
   }
   ```

2. 包引用

   ```groovy
   implementation 'com.github.yunzhouhua:AdvertSwitcher:0.1.0'
   ```

3. 布局引用

   ```xml
   <com.yunzhou.advertswitcher.AdvertSwitcher
   	android:id="@+id/id"
   	android:layout_width="match_parent"
   	android:layout_height="80dp"/>
   ```

4. 数据源绑定

   ```java
   advertSwitcher.setAdapter(new IAdvertAdapter());
   ```

5. 生命周期

   在onResume()中启动滚动动画，在onPause中暂停

   ```java
   @Override
   protected void onResume() {
       super.onResume();
       advertSwitcher.start();
   }

   @Override
   protected void onPause() {
       super.onPause();
       advertSwitcher.stop();
   }
   ```

6. 刷新，数据源发生改变时刷View，使新数据优先显示，同时新的数据应当添加在数据源的前面

   ```java
   advertSwitcher.refresh();
   ```



#### 属性配置

属性在XML中直接配置

| 属性           | 类型      | 默认值                           | 描述         |
| ------------ | ------- | ----------------------------- | ---------- |
| timeSpan     | int(毫秒) | 3000                          | 轮播事件间隔     |
| inAnim       | resId   | Y轴0% -> 100%的滚动               | 轮播View进入动画 |
| outAnim      | redId   | Y轴0% -> -100%的滚动              | 轮播View离开动画 |
| interpolator | resId   | android.R.interpolator.linear | 轮播动画插值器    |


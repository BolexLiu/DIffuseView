#DIffuseView

 - 一个使用了ValueAnimator的扩散动画的View
 - design:25.0.1
 - cardview-v7:25.0.1
 - material-menu:2.0.0
 - transition 转场动画

 ``` java
         Intent intent = new Intent(view.getContext(), ViewDetailActivity.class);
                         intent.putExtra("title", "DiffuseImgView");
                         if (Build.VERSION.SDK_INT >= 21) {
                             // 使用api11 新加 api的方法
                             String transitionName = view.getContext().getString(R.string.imgbreak);
                             ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), view, transitionName);
                             view.getContext().startActivity(intent, transitionActivityOptions.toBundle());
                         } else {
                             view.getContext().startActivity(intent);
                         }
 ```

![](https://github.com/yt7789451/DIffuseView/blob/master/show.gif)



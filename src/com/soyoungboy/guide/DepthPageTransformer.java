package com.soyoungboy.guide;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 
 * @ClassName: DepthPageTransformer
 * @Description: TODO(viewPager切换动画)
 * 
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DepthPageTransformer implements ViewPager.PageTransformer {

	private static float MIN_SCALE = 0.75f;

	/**
	 * @Name transformPage
	 * @Description TODO(这里用一句话描述这个方法的作用)
	 * @param view
	 *            滑动中的那个view
	 * @param position
	 *            position这里是float类型，不是平时理解的int位置，而是当前滑动状态的一个表示，比如当滑动到正全屏时，
	 *            position是0
	 *            ，而向左滑动，使得右边刚好有一部被进入屏幕时，position是1，如果前一页和下一页基本各在屏幕占一半时
	 *            ，前一页的position是
	 *            -0.5，后一页的posiotn是0.5，所以根据position的值我们就可以自行设置需要的alpha，x/y信息
	 * @see android.support.v4.view.ViewPager.PageTransformer#transformPage(android.view.View,
	 *      float)
	 * @Date 2015-5-25 下午3:13:18
	 **/
	@Override
	public void transformPage(View view, float position) {
		// TODO Auto-generated method stub
		int pageWidth = view.getWidth();

		if (position < -1) {
			//前一页
			view.setAlpha(0);
		} else if (position <= 0) {
			view.setAlpha(1);
			view.setTranslationX(0);
			view.setScaleX(1);
			view.setScaleY(1);
		} else if (position <= 1) {
			//后一页
			view.setAlpha(1 - position);
			view.setTranslationX(pageWidth * -position);
			float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
					* (1 - Math.abs(position));
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);
		} else {
			view.setAlpha(0);
		}
	}

}

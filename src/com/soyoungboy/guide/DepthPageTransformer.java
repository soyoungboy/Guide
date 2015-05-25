package com.soyoungboy.guide;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 
 * @ClassName: DepthPageTransformer
 * @Description: TODO(viewPager�л�����)
 * 
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DepthPageTransformer implements ViewPager.PageTransformer {

	private static float MIN_SCALE = 0.75f;

	/**
	 * @Name transformPage
	 * @Description TODO(������һ�仰�����������������)
	 * @param view
	 *            �����е��Ǹ�view
	 * @param position
	 *            position������float���ͣ�����ƽʱ����intλ�ã����ǵ�ǰ����״̬��һ����ʾ�����統��������ȫ��ʱ��
	 *            position��0
	 *            �������󻬶���ʹ���ұ߸պ���һ����������Ļʱ��position��1�����ǰһҳ����һҳ����������Ļռһ��ʱ
	 *            ��ǰһҳ��position��
	 *            -0.5����һҳ��posiotn��0.5�����Ը���position��ֵ���ǾͿ�������������Ҫ��alpha��x/y��Ϣ
	 * @see android.support.v4.view.ViewPager.PageTransformer#transformPage(android.view.View,
	 *      float)
	 * @Date 2015-5-25 ����3:13:18
	 **/
	@Override
	public void transformPage(View view, float position) {
		// TODO Auto-generated method stub
		int pageWidth = view.getWidth();

		if (position < -1) {
			//ǰһҳ
			view.setAlpha(0);
		} else if (position <= 0) {
			view.setAlpha(1);
			view.setTranslationX(0);
			view.setScaleX(1);
			view.setScaleY(1);
		} else if (position <= 1) {
			//��һҳ
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

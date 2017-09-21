package us.mifeng.utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class MyDecor extends ItemDecoration{
	//����һ��ˮƽ����ֱ����
	private final int Horization=RecyclerView.HORIZONTAL;
	private final int Vertical=RecyclerView.VERTICAL;
	//����һ��int�͵�����,�������д��android���Դ��ķָ���
	private int[] attrs={android.R.attr.listDivider};
	//����һ��ͼƬ����
	private Drawable drawable;
	//����һ��RecyclerView�ķ���
	private int oritation;
	public MyDecor(Context ctx, int orization) {
		TypedArray array=ctx.obtainStyledAttributes(attrs);
		drawable=array.getDrawable(0);
		array.recycle();//���գ���ֹ�ڴ����
		//�жϷ���
		if(orization!=Horization&&orization!=Vertical){
			//����Ȳ���ˮƽ�ֲ�����ֱ�ģ��׳��쳣
			throw new IllegalArgumentException("û�иķ���");
		}else{
			oritation=orization;
		}
	}
	@Override
	public void onDraw(Canvas c, RecyclerView parent, State state) {
		// TODO Auto-generated method stub
		super.onDraw(c, parent, state);
		if(oritation==Vertical){
			drawHor(c,parent);
		}
		if(oritation==Horization){
			drawVer(c,parent);
		}
	}
	
	//ˮƽ�����һ����
	private void drawHor(Canvas c, RecyclerView parent) {
		int left=parent.getPaddingLeft();
		int right=parent.getWidth()-parent.getPaddingRight();
		int itemCount=parent.getChildCount();
		for(int i=0;i<itemCount;i++){
			View child=parent.getChildAt(i);
			//���item��view��ͼ�Ĳ�������
			LayoutParams params=(LayoutParams) child.getLayoutParams();
			//���ݲ��������ȡ����ֵ
			int top=child.getBottom()+params.bottomMargin;
			//����bottom����
			int bottom=top+drawable.getIntrinsicHeight();
			drawable.setBounds(left, top, right, bottom);
			//���ڻ�����
			drawable.draw(c);
		}
	}
	//��ֱ����һ����
	private void drawVer(Canvas c, RecyclerView parent) {

		int top=parent.getPaddingTop();
		int bottom=parent.getHeight()-parent.getPaddingBottom();
		int itemCount=parent.getChildCount();
		for(int i=0;i<itemCount;i++){
			View child=parent.getChildAt(i);
			//���item��view��ͼ�Ĳ�������
			LayoutParams params=(LayoutParams) child.getLayoutParams();
			//���ݲ��������ȡ����ֵ
			int left=child.getRight()+params.rightMargin;
			//����bottom����
			int right=left+drawable.getIntrinsicWidth();
			drawable.setBounds(left, top, right, bottom);
			//���ڻ�����
			drawable.draw(c);
		}
	
		
	}
	@Override
	public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
		// TODO Auto-generated method stub
		super.getItemOffsets(outRect, itemPosition, parent);
	}
	
}

package com.project.base.resource.component;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.kycq.library.refresh.RecyclerAdapter;

class SpaceItemDecoration extends RecyclerAdapter.ItemDecoration {
	private int spaceSize;
	
	SpaceItemDecoration(Context context) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		
		this.spaceSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, displayMetrics);
	}
	
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		if (isSkipDraw(view, parent, state)) {
			return;
		}
		
		// RecyclerView.Adapter adapter = parent.getAdapter();
		// int position = parent.getChildAdapterPosition(view);
		outRect.bottom = this.spaceSize;
		// if (position == 0) {
		// 	outRect.left = this.boundarySize;
		// }
		// if (adapter.getItemCount() == position + 1) {
		// 	outRect.right = this.boundarySize;
		// }
	}
}

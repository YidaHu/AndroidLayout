package com.gswtek.huyd.androidapipro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gswtek.huyd.customappcompat.CardViewMainActivity;
import com.gswtek.huyd.customdesign.AppBarMainActivity;
import com.gswtek.huyd.layouts.GridLayoutMainActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomAppCompatActivity extends AppCompatActivity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_app_compat);

		listView = (ListView) findViewById(R.id.customAppCompatListView);
		//我们设置一个List集合，然后向里边添加几条数据
		List<String> ls = new ArrayList<String>();
		ls.add("CardView");
		ls.add("GridLayout");
		ls.add("RecyclerView");
		ls.add("Toolbar");

		//获取xml文件中listView控件
		//然后为listView控件调用setAdapter方法，让数据显示在界面上。
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ls));

		//为listView对象进行监听：当点击子项目的时候触发
		listView.setOnItemClickListener(new ItemClickEvent());
	}

	//继承OnItemClickListener，当子项目被点击的时候触发
	private final class ItemClickEvent implements AdapterView.OnItemClickListener {
		//这里需要注意的是第三个参数arg2，这是代表单击第几个选项
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
			//通过单击事件，获得单击选项的内容
			String text = listView.getItemAtPosition(i) + "";
			//通过吐丝对象显示出来。
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
			if (demos.length > i) {
				Intent intent = new Intent(CustomAppCompatActivity.this, demos[i].demoClass);
				startActivity(intent);
			}

		}
	}

	//把每个activity转成class
	private static class DemoInfo {
		private final Class<? extends android.app.Activity> demoClass;

		public DemoInfo(Class<? extends android.app.Activity> demoClass) {
			this.demoClass = demoClass;
		}
	}

	//把每个activity转成xxx.class
	private static final DemoInfo[] demos = {
			new DemoInfo(CardViewMainActivity.class),
			new DemoInfo(GridLayoutMainActivity.class),
			new DemoInfo(CardViewMainActivity.class),
			new DemoInfo(AppBarMainActivity.class),
	};
}

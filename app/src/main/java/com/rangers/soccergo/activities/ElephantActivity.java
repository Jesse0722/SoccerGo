package com.rangers.soccergo.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.rangers.soccergo.R;
import com.rangers.soccergo.entities.Elephant;
import com.rangers.soccergo.entities.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * ElephantActivity
 * Desc:
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 17:05
 * Created by: Wooxxx
 */
public class ElephantActivity extends BaseActivity {
    private ViewFlipper flipper;
    private EditText elephantNameEditor;
    private EditText elephantWeightEditor;
    private EditText fruitNameEditor;
    private TextView showNameTxt;
    private TextView showWeightTxt;
    private Button elephantSubmit;
    private Button fruitSubmit;
    private Button showBtn;
    private Button backBtn;
    private ListView listView;
    private Elephant elephant = new Elephant();
    private List<Fruit> fruits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elephant);
        //初始化各组件
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        elephantNameEditor = (EditText) findViewById(R.id.elephant_name);
        elephantWeightEditor = (EditText) findViewById(R.id.elephant_weight);
        fruitNameEditor = (EditText) findViewById(R.id.fruit_name);
        showNameTxt = (TextView) findViewById(R.id.show_name);
        showWeightTxt = (TextView) findViewById(R.id.show_weight);
        elephantSubmit = (Button) findViewById(R.id.elephant_submit);
        fruitSubmit = (Button) findViewById(R.id.fruit_submit);
        showBtn = (Button) findViewById(R.id.info_show_btn);
        backBtn = (Button) findViewById(R.id.back_btn);
        listView = (ListView) findViewById(R.id.list);

        bindingBtns();

    }

    private void bindingBtns() {
        //提交小象按钮绑定
        elephantSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String elephantName = elephantNameEditor.getText()
                        .toString();
                Double elephantWeight = Double.valueOf(
                        elephantWeightEditor.getText().toString());
                /*
                * 这里就是创建及保存数据对象的BLOCK,
                * 下面我们创建一直小大象
                * */
                elephant.setName(elephantName);
                elephant.setWeight(elephantWeight);
                //建议用异步的保存方式，在回调接口里做一些后续操作
                elephant.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        //创建完成我们跳转到添加水果页面
                        if (e == null)
                            flipper.showNext();
                        else
                            e.printStackTrace();
                    }
                });
            }
        });

        //添加水果按钮
        fruitSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fruitName = fruitNameEditor.getText()
                        .toString();
                /* 下面我们为小大象添加他爱吃的水果 */
                final Fruit fruit = new Fruit();
                fruit.setName(fruitName);
                fruit.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        //创建成功我们弹窗显示一下
                        if (e == null) {
                            //将水果添加到小象喜欢的水果列表中
                            elephant.addLike(fruit);
                            elephant.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(AVException e) {
                                    if (e == null)
                                        Toast.makeText(ElephantActivity.this, "添加成功", Toast.LENGTH_SHORT)
                                                .show();
                                    else
                                        e.printStackTrace();
                                }
                            });

                        } else
                            e.printStackTrace();
                    }
                });
            }
        });

        //查看信息按钮
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * 在显示小大象爱吃的水果列表前查询出水果
                * */
                elephant.getLikes(new FindCallback<Fruit>() {
                    @Override
                    public void done(List<Fruit> list, AVException e) {
                        fruits = list;

                        BaseAdapter adapter = new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return fruits.size();
                            }

                            @Override
                            public Fruit getItem(int position) {
                                return fruits.get(position);
                            }

                            @Override
                            public long getItemId(int position) {
                                return position;
                            }

                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                Fruit fruit = fruits.get(position);
                                if (convertView == null) {
                                    convertView = LayoutInflater.from(ElephantActivity.this)
                                            .inflate(R.layout.item_fruits, parent, false);
                                }
                                TextView nameTxt = (TextView) convertView.findViewById(R.id.name);
                                nameTxt.setText(fruit.getName());
                                return convertView;
                            }
                        };

                        listView.setAdapter(adapter);
                        showNameTxt.setText(elephant.getName());
                        showWeightTxt.setText(elephant.getWeight().toString());
                        flipper.showNext();
                    }
                });
            }
        });

        //返回填写水果
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.showNext();
            }
        });

    }


}


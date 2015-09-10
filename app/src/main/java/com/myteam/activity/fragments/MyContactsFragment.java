package com.myteam.activity.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myteam.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yum on 2015/9/8.
 */
public class MyContactsFragment extends Fragment implements ExpandableListView.OnChildClickListener,ExpandableListView.OnGroupClickListener,View.OnClickListener{
    Context context;
    LinearLayout layout_contacts;//好友列表整体view

    public  MyContactsFragment(){
        super();
    }

     public  MyContactsFragment(Context context){
        super();
        this.context = context;
    }
    ExpandableListView listView;//好友列表
    List<String> groupList;//组名
    List<List<String>> childLists;//好友名
    LayoutInflater inflater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null)
            return  null;
        this.inflater = inflater;
        initList();
        layout_contacts = (LinearLayout)inflater.inflate(R.layout.layout_contacts,container,false);
        listView = (ExpandableListView)layout_contacts.findViewById(R.id.expandListView);
        listView.setAdapter(new MyExpandableListAdapter());
        setExpandableListViewListener();//组，好友的监听
        setListView();//listview相关设置
        setLayoutContactsTopOnClick();//头顶三个按钮监听事件
        return layout_contacts;
    }
    private void setLayoutContactsTopOnClick() {
        layout_contacts.findViewById(R.id.message_top).setOnClickListener(this);
        layout_contacts.findViewById(R.id.contacts_top).setOnClickListener(this);
        layout_contacts.findViewById(R.id.add_top).setOnClickListener(this);
    }
    private void setListView() {
        listView.setGroupIndicator(null);//隐藏默认箭头
        listView.setVerticalScrollBarEnabled(false);//滚动条隐藏
    }
    private void setExpandableListViewListener() {
        listView.setOnChildClickListener(this);
        listView.setOnGroupClickListener(this);
    }
    private void initList() {
        groupList = new ArrayList<String>();
        childLists = new ArrayList<List<String>>();
        addInfo("fffff",new String[]{"回来","回来","回来"});
        addInfo("第二组",new String[]{"aaa","bb","cccc","dd","eeeeeee"});
        addInfo("第三组",new String[]{"十大","十大","十大"});
        addInfo("第四组",new String[]{"大大倒萨大","大大倒萨大"});
        addInfo("第五组",new String[]{"对萨达萨达","的撒旦撒的撒大大飒飒大萨达的撒打算的撒旦撒的撒大大飒飒大萨达的撒打算的撒旦撒的撒大大飒飒大萨达的撒打算"});
        addInfo("第六组",new String[]{"大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大","大大倒萨大"});
    }
    private void addInfo(String groupName,String[] childs) {
        groupList.add(groupName);
        List<String> list= new ArrayList<String>();
        for (int i = 0; i< childs.length; i++){
            list.add(childs[i]);
        }
        childLists.add(list);
    }
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        TextView textView = (TextView) view.findViewById(R.id.item_contacts_textview1);
        Toast.makeText(context, "第" + i + "组,第" + i1 + "个好友,名字:" + textView.getText().toString(), Toast.LENGTH_SHORT).show();
        return false;
    }
    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
        TextView textView = (TextView) view.findViewById(R.id.item_contacts_textview0);
        Toast.makeText(context, "第" + i + "组，名字：" + textView.getText().toString(), Toast.LENGTH_SHORT).show();
        return false;
    }
    public void onClick(View view) {//头顶按钮监听
        switch (view.getId()){
            case R.id.message_top:
                Toast.makeText(context, "消息", Toast.LENGTH_SHORT).show();
            case R.id.contacts_top:
                Toast.makeText(context, "联系人", Toast.LENGTH_SHORT).show();
            case R.id.add_top:
                Toast.makeText(context, "添加", Toast.LENGTH_SHORT).show();
        }
    }
    public void addGroup(String groupName){
        groupList.add(groupName);
    }
    public void addChild(int position,String childName){//在指定组添加好友名字
        childLists.get(position).add(childName);
    }
    private  class MyExpandableListAdapter extends BaseExpandableListAdapter {
        public int getGroupCount() {
            return groupList.size();
        }
        public int getChildrenCount(int i) {
            return childLists.get(i).size();
        }
        public Object getGroup(int i) {
            return groupList.get(i);
        }
        public Object getChild(int i, int i1) {
            return childLists.get(i).get(i1);
        }
        public long getGroupId(int i) {
            return i;
        }
        public long getChildId(int i, int i1) {
            return i1;
        }
        public boolean hasStableIds() {
            return false;
        }
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(context);
            TextView textView = null;
            if (view == null){
                view = inflater.inflate(R.layout.item_fragment_contacts_group,null);
                ViewHolderForGroup viewHolderForGroup = new ViewHolderForGroup();
                textView = (TextView) view.findViewById(R.id.item_contacts_textview0);
                viewHolderForGroup.textView01 = textView;
                view.setTag(viewHolderForGroup);
            }else{//使用缓存，免得每次都要查询
                ViewHolderForGroup viewHolderForGroup = (ViewHolderForGroup) view.getTag();
                textView = viewHolderForGroup.textView01;
            }

            if (b){//如果是伸展
                view.setBackgroundResource(R.drawable.ic_preference_first_normal);
            }else{
                view.setBackgroundResource(R.drawable.ic_preference_single_normal);
            }
            String s = groupList.get(i);
            textView.setText(s);
            return view;
        }
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            ImageView imageView = null;
            TextView textView = null;
            String s = childLists.get(i).get(i1);
            LayoutInflater inflater = LayoutInflater.from(context);
            if (view == null){//每一个item view第一次肯定会进入
                view = inflater.inflate(R.layout.item_fragment_contacts_child, null);
                ViewHolderForChilds viewHolderForChilds = new ViewHolderForChilds();
                imageView = (ImageView) view.findViewById(R.id.item_contacts_img1);
                textView = (TextView) view.findViewById(R.id.item_contacts_textview1);
                viewHolderForChilds.imageView01 = imageView;
                viewHolderForChilds.textView01 = textView;
                view.setTag(viewHolderForChilds);
            }else{
                ViewHolderForChilds viewHolderForChilds = (ViewHolderForChilds) view.getTag();
                imageView = viewHolderForChilds.imageView01;
                textView = viewHolderForChilds.textView01;
            }
            if (b) {//如果是最后一个
                view.setBackgroundResource(R.drawable.ic_preference_last_normal);
            }else {
                view.setBackgroundResource(R.drawable.ic_preference_normal);
            }
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.face1));
            textView.setText(s);
            return view;
        }
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
    private class ViewHolderForGroup {
        public TextView textView01;
    }
    private class ViewHolderForChilds {
        public ImageView imageView01;
        public TextView textView01;
    }
}

package us.mifeng.erjiliandong.ui.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.mifeng.erjiliandong.R;
import us.mifeng.erjiliandong.ui.base.BaseScrollableContainer;
import us.mifeng.erjiliandong.ui.content.RecyclerViewContentContainer;
import us.mifeng.erjiliandong.ui.tab.ListViewTabContainer;
import us.mifeng.erjiliandong.ui.widget.RealSectionIndexer;
import us.mifeng.erjiliandong.ui.widget.SimpleArrayAdapter;

public class MainActivity extends AppCompatActivity {

    private LinkedLayout mLinkedLayout;
    private BaseScrollableContainer mTabContainer;      // 左边的 Tab 页
    private BaseScrollableContainer mContentContainer;  // 右边的 content 页

    List<Integer> mData = Stream.iterate(0, item -> item+1)
            .limit(500)
            .collect(Collectors.toList());

    private SectionIndexer mSectionIndexer = new RealSectionIndexer(mData);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinkedLayout = (LinkedLayout) findViewById(R.id.linked_layout);

        initTabContainer();
        initContentContainer();
        initLinkedLayout();
    }
    private void initTabContainer() {
        ListView mListView = new ListView(this);
        mListView.setAdapter(new ArrayAdapter<>(this, R.layout.item_common,
                Arrays.asList(mSectionIndexer.getSections())
        ));

        mTabContainer = new ListViewTabContainer(this, mListView);
    }

    private void initContentContainer() {
        RecyclerView mRecyclerView = new RecyclerView(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new SimpleArrayAdapter<>(this, mData, mSectionIndexer));

        mContentContainer = new RecyclerViewContentContainer(this, mRecyclerView);
    }

    private void initLinkedLayout() {
        mLinkedLayout.setContainers(mTabContainer, mContentContainer);
        mLinkedLayout.setSectionIndexer(mSectionIndexer);
    }
}

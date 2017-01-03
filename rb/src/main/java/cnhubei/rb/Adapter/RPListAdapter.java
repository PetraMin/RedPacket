package cnhubei.rb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cnhubei.rb.Data.HongbaoInfo;
import cnhubei.rb.R;

public class RPListAdapter extends BaseAdapter {
    private Context context;
    private List<HongbaoInfo> list;

    public RPListAdapter(Context context, List<HongbaoInfo> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_rp_infor, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_name_introduce = (TextView) convertView.findViewById(R.id.tv_name_introduce);
            holder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
            holder.tv_money_introduce = (TextView) convertView.findViewById(R.id.tv_money_introduce);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_time_introduce = (TextView) convertView.findViewById(R.id.tv_time_introduce);
            convertView.setTag(holder); //绑定ViewHolder对象
        }
        else {
            holder = (ViewHolder) convertView.getTag(); //取出ViewHolder对象
        }

        if (position == 0){
            holder.tv_name_introduce.setVisibility(View.VISIBLE);
            holder.tv_money_introduce.setVisibility(View.VISIBLE);
            holder.tv_time_introduce.setVisibility(View.VISIBLE);
        } else {
            holder.tv_name_introduce.setVisibility(View.GONE);
            holder.tv_money_introduce.setVisibility(View.GONE);
            holder.tv_time_introduce.setVisibility(View.GONE);
        }

        holder.tv_name.setText(list.get(position).getSender());
        holder.tv_money.setText(""+list.get(position).getMoney());
        holder.tv_time.setText(list.get(position).getTime());
        return convertView;
    }

    /*存放控件 的ViewHolder*/
    public final class ViewHolder {
        private TextView tv_name, tv_money, tv_time, tv_name_introduce, tv_money_introduce, tv_time_introduce;

    }
}

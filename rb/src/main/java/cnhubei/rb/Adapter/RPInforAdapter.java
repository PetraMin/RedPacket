package cnhubei.rb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cnhubei.rb.Data.HongbaoInfo;
import cnhubei.rb.R;

public class RPInforAdapter extends RecyclerView.Adapter<RPInforAdapter.RPViewHoled> {
    private Context mContext;
    private List<HongbaoInfo> list;

    public RPInforAdapter(Context context, List<HongbaoInfo> list){
        mContext = context;
        this.list = list;
    }

    @Override
    public RPViewHoled onCreateViewHolder(ViewGroup parent, int viewType) {
        RPViewHoled holed = new RPViewHoled(LayoutInflater.from(mContext).inflate(R.layout.item_rp_infor, parent));
        return holed;
    }

    @Override
    public void onBindViewHolder(RPViewHoled holder, int position) {
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
        holder.tv_name.setText(""+list.get(position).getMoney());
        holder.tv_name.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RPViewHoled extends RecyclerView.ViewHolder{
        private TextView tv_name, tv_money, tv_time, tv_name_introduce, tv_money_introduce, tv_time_introduce;

        public RPViewHoled(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name_introduce = (TextView) view.findViewById(R.id.tv_name_introduce);
            tv_money = (TextView) view.findViewById(R.id.tv_money);
            tv_money_introduce = (TextView) view.findViewById(R.id.tv_money_introduce);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_time_introduce = (TextView) view.findViewById(R.id.tv_time_introduce);
        }
    }
}

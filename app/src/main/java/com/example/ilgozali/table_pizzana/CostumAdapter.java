package com.example.ilgozali.table_pizzana;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ilgozali.table_pizzana.model.ModelData;

import java.util.List;

public class CostumAdapter extends RecyclerView.Adapter<CostumAdapter.CustomViewHolder> {

    private List<ModelData> dataList;
    private Context context;


    public CostumAdapter(Context context, List<ModelData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        ModelData md;

        public TextView tvcustno, tvstart, tvfinish, tvstatus;
        CardView parentLayout;


        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tvcustno = mView.findViewById(R.id.tx_customer);
            tvstart = mView.findViewById(R.id.tx_mulai);
            tvfinish = mView.findViewById(R.id.tx_selesai);
            tvstatus = mView.findViewById(R.id.tx_status);
            parentLayout = mView.findViewById(R.id.parent_layout);


        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.activity_costum_adapter, parent, false);
//        return new CustomViewHolder(view);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_costum_adapter, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        holder.tvcustno.setText(dataList.get(position).getCustno());
        holder.tvstart.setText(dataList.get(position).getStart());
        holder.tvfinish.setText(dataList.get(position).getFinish());
        holder.tvstatus.setText(dataList.get(position).getStatus());


        if (dataList.get(position).getStatus().equals("DIPROSES"))
            holder.tvstatus.setBackgroundResource(R.drawable.background_proses);
        else if (dataList.get(position).getStatus().equals("SELESAI"))
            holder.tvstatus.setBackgroundResource(R.drawable.background_selesai);
        else if (dataList.get(position).getStatus().equals("BATAL"))
            holder.tvstatus.setBackgroundResource(R.drawable.backgrond_batal);
        else


            Log.d("Mainactivity", "data:" + dataList.size());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, Detail.class);
//                Toast.makeText(context, "Status : " + dataList.get(position).getStatusTable(), Toast.LENGTH_SHORT).show();
//                intent.putExtra("costno", dataList.get(position).getIdCostummer());
//                intent.putExtra("start", dataList.get(position).getMulai());
//                intent.putExtra("finish", dataList.get(position).getSelesai());
//                intent.putExtra("status", dataList.get(position).getStatusTable());
//                context.startActivity(intent);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.ma);
                builder.setTitle("Update Pesanan");
                builder.setMessage("Apakah Pesanan Sudah:");

                builder.setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("status", dataList.get(position).getStatus());
                        holder.tvstatus.setText("Selesai");
                        holder.tvstatus.setBackgroundResource(R.drawable.background_selesai);
                        context.startActivity(intent);

                    }


                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
        @Override
        public int getItemCount () {

            return dataList.size();
        }
    }

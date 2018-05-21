package com.nontification.checknamestudent.checkstudent.RecycelView;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nontification.checknamestudent.checkstudent.R;

import java.util.List;

import okhttp3.ResponseBody;

public class RecycleViewAdapter1 extends RecyclerView.Adapter<RecycleViewAdapter1.MyHoder> {

    Context context0;
    List<String> nData;
    String member_id = "";

    AlertDialog dialog;


    public RecycleViewAdapter1(Context context) {

        this.context0 = context;

    }

    public void Load_data(List<String> nData, String member_id) {

        this.nData = nData;
        this.member_id = member_id;

    }

    @NonNull
    @Override
    public RecycleViewAdapter1.MyHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View T;
        T = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_history_check,
                parent, false);

        return new MyHoder(T, context0);

    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter1.MyHoder holder, int position) {


        holder.tv_name.setText(nData.get(position));
        holder.setOnClickRecycleView(new OnClickRecycleView() {
            @Override
            public void onClick(View view, int position, boolean isLongClick, MotionEvent motionEvent) {

                Toast.makeText(context0, "โอยย โอยยย", Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onLongClick(View view, int position, boolean isLongClick, MotionEvent motionEvent) {
//                Toast.makeText(context0, "onlongclick", Toast.LENGTH_SHORT).show();

                    showCustomDialog(context0, position);

            }


        });

    }


    public void removeItem(int position) {
        nData.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return nData.size();
    }

    class MyHoder extends RecyclerView.ViewHolder implements View.OnClickListener
            , View.OnLongClickListener, View.OnTouchListener {

        private OnClickRecycleView onClickRecycleView;
        TextView tv_name;
        Context context;

        public MyHoder(View itemView,Context context) {

            super(itemView);

            tv_name = itemView.findViewById(R.id.row_for_history_check);
            this.context = context;


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        public void setOnClickRecycleView(OnClickRecycleView onClickRecycleView){
            this.onClickRecycleView =  onClickRecycleView;

        }

        @Override
        public void onClick(View v) {
            onClickRecycleView.onClick(v, getAdapterPosition(), false, null);

        }

        @Override
        public boolean onLongClick(View v) {
            onClickRecycleView.onLongClick(v, getAdapterPosition(), false, null);
            return true;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            onClickRecycleView.onClick(v, getAdapterPosition(), false, null);
            return true;
        }
    }

    public void showCustomDialog(final Context context , final int po) {



        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View handleView = layoutInflater.inflate(R.layout.custom_dialog_stu, null);
        Button button_yes = handleView.findViewById(R.id.btn_yes);



        builder.setView(handleView);
        button_yes.setOnClickListener(new View.OnClickListener() {

//            OnNetworkCallback_Del_data onNetworkCallback_del_data = new OnNetworkCallback_Del_data() {
//                @Override
//                public void onResponse(POJO_Del del) {
//
//                    removeItem(po);
//
//                    dialog.dismiss();
////                    if (){
////                    Toast.makeText(context, "ลบข้อมูลสำเร็จ โปรดออกแล้วเข้าใหม่", Toast.LENGTH_SHORT).show();
////                    }
//
//
//                }
//
//                @Override
//                public void onBodyError(ResponseBody responseBodyError) {
//                    Toast.makeText(context, "responseBodyError", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onBodyErrorIsNull() {
//                    Toast.makeText(context, "res is null", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//                    Toast.makeText(context, "Err "+t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            };

            @Override
            public void onClick(View v) {

//                new NetworkConnectionManager().callServer_del(onNetworkCallback_del_data,app_id.get(po).toString());

            }
        });



        Button button_no = handleView.findViewById(R.id.btn_no);
        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });


        dialog =  builder.show();
    }



}

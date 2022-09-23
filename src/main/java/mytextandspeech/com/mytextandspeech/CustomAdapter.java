package mytextandspeech.com.mytextandspeech;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;

import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList<ListenAndSpeakModal> template;
    CustomAdapter(Context context, ArrayList<ListenAndSpeakModal> template){
       this.context = context;
       this.template = template;
       }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView template_id, template_name, template_description;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            template_id = itemView.findViewById(R.id.templateid);
            template_name = itemView.findViewById(R.id.templatename);
            template_description = itemView.findViewById(R.id.templatedescription);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.my_row, viewGroup, false);
       // view.setOnClickListener(RecycleviewActivity.myOnClickListener);
        return new MyViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.template_id.setText(String.valueOf(template.get(i).id));
        myViewHolder.template_name.setText(template.get(i).templateName);
        myViewHolder.template_description.setText(template.get(i).templateDescription);

    }

    @Override
    public int getItemCount() {
        return template.size();
    }
}

package jones.wilfred.projectlocalizationbase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private String[] colors;

    public DrawerAdapter(String[] colors) {
        this.colors = colors;
    }

    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.drawer_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerAdapter.ViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View colorSwatch;
        TextView textView;
        int position;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            colorSwatch = itemView.findViewById(R.id.colorSwatch);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)view.getContext()).updateScreen(position);
                }
            });
        }

        public void update(int position) {
            this.position = position;
            textView.setText(colors[position]);
            colorSwatch.setBackgroundColor(Colors.codes[position]);
        }
    }
}

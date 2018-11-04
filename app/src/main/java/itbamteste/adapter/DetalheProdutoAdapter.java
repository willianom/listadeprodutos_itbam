package itbamteste.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import itbamteste.bean.ProdutosBean;
import itbamteste.listadeprodutos.R;
import itbamteste.util.RoundedCornersTransformation;

public class DetalheProdutoAdapter extends RecyclerView.Adapter<DetalheProdutoAdapter.SingleItemRowHolder> {

    private FragmentActivity  mContext;
    private List<ProdutosBean> itemsList;

    public static int sCorner = 15;
    public static int sMargin = 2;

    public DetalheProdutoAdapter(FragmentActivity mContext, List<ProdutosBean> itemsList) {
        this.mContext = mContext;
        this.itemsList = itemsList;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detalhe_produto, parent, false);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                    ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);

        DetalheProdutoAdapter.SingleItemRowHolder mh = new DetalheProdutoAdapter.SingleItemRowHolder(mContext, v);

        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int position) {

        ProdutosBean singleItem = itemsList.get(position);

        String nomeProduto  = singleItem.getName();
        String descProduto  = singleItem.getDescription();
        String urlImage     = singleItem.getUrl();

        holder.txtNomeProduto.setText(nomeProduto);
        holder.txtDescProduto.setText(descProduto);

        Glide.with (mContext)
                .load(urlImage)
                .thumbnail (0.5f)
                .centerCrop()
                .bitmapTransform(new RoundedCornersTransformation( mContext,sCorner, sMargin))
                .diskCacheStrategy (DiskCacheStrategy.ALL)
                .into(holder.imgProduto);
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected ImageView imgProduto;
        protected TextView txtNomeProduto;
        protected TextView txtDescProduto;
        protected Button btnVoltar;
        protected FragmentActivity contextHolder;

        public SingleItemRowHolder(FragmentActivity context, View itemView) {
            super(itemView);

            imgProduto     = (ImageView) itemView.findViewById(R.id.imgdet_produto);
            txtNomeProduto = (TextView) itemView.findViewById(R.id.txt_nomedet_produto);
            txtDescProduto = (TextView) itemView.findViewById(R.id.txt_desc_produto);
            btnVoltar      = (Button) itemView.findViewById(R.id.btn_voltar);
            contextHolder  = context;

            btnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contextHolder.getSupportFragmentManager().popBackStack();
                }
            });

        }
    }
}

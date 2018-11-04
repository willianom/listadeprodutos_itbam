package itbamteste.listadeprodutos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import itbamteste.adapter.DetalheProdutoAdapter;
import itbamteste.bean.ProdutosBean;

public class FragmentDetalheProduto extends Fragment {

    private Toolbar   toolbar;
    private RecyclerView mRecyclerView;

    private ProdutosBean beanProduto;
    private List<ProdutosBean> listProduto;

    private DetalheProdutoAdapter listAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu (true);

        Bundle args = getArguments ();
        beanProduto = (ProdutosBean) args.getSerializable("bean_produto");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate (R.layout.fragment_view_detalhe_produto , container, false);

        LinearLayoutManager llm = new LinearLayoutManager (getActivity ());
        llm.setOrientation (LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) view.findViewById (R.id.recycler_detalhe_produto);
        mRecyclerView.setHasFixedSize (true);
        mRecyclerView.setLayoutManager (llm);

        listProduto = new ArrayList<ProdutosBean>();
        listProduto.add(beanProduto);

        listAdapter = new DetalheProdutoAdapter(getActivity (), listProduto);
        mRecyclerView.setAdapter (listAdapter);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        toolbar.setTitle("Detalhe do Produto");
        toolbar.setNavigationIcon(R.drawable.ic_back_holo_dark);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}

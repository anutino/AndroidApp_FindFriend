package com.findfriend.ui.fragment

/*class AnimalImageSlidePageFragment : Fragment() {
    private var mUserView: TextView? = null

    //companion object {
        fun getInstance(name: String): Fragment {
            val fragment = AnimalImageSlidePageFragment()
            val arg = Bundle()
            arg.putString("name", name)
            fragment.arguments = arg
            return fragment
        }
   // }
    val ARGUMENT_PAGE_NUMBER = "arg_page_number"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.item_media, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserView = view.findViewById(R.id.imageViewAnimal)
        val name = arguments?.getString("name")
        mUserView?.text = "Hello $name"
    }

     fun newInstance(page: Int): AnimalImageSlidePageFragment {
        val pageFragment = AnimalImageSlidePageFragment()
        val arguments = Bundle()
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page)
        pageFragment.setArguments(arguments)
        return pageFragment
    }
}*/
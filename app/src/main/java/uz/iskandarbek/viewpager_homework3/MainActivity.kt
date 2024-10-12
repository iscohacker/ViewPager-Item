package uz.iskandarbek.viewpager_homework3

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import uz.iskandarbek.viewpager_homework3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var myPagerAdapter: MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myPagerAdapter = MyPagerAdapter()
        binding.viewPager.adapter = myPagerAdapter
        binding.myTab.setupWithViewPager(binding.viewPager)

        setupTabIcons()

        binding.next.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem < myPagerAdapter.count - 1) {
                binding.viewPager.currentItem = currentItem + 3
            }
        }
        binding.reg.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem < myPagerAdapter.count - 1) {
                binding.viewPager.currentItem = currentItem + 1
            }
        }

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (positionOffset > 0) {
                    val currentTab = binding.myTab.getTabAt(position)
                    val nextTab = binding.myTab.getTabAt(position + 1)

                    val currentImageView = currentTab?.customView?.findViewById<ImageView>(R.id.selectedd)
                    val nextImageView = nextTab?.customView?.findViewById<ImageView>(R.id.selectedd)

                    currentImageView?.alpha = 1 - positionOffset
                    nextImageView?.alpha = positionOffset
                }
            }

            override fun onPageSelected(position: Int) {
                updateTabIndicators(position)

                if (position == myPagerAdapter.count - 1) {
                    binding.next.visibility = View.GONE
                } else {
                    binding.next.visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun setupTabIcons() {
        for (i in 0 until myPagerAdapter.count) {
            val tab = binding.myTab.getTabAt(i)
            if (tab != null) {
                tab.customView = LayoutInflater.from(this).inflate(R.layout.item_tab, null)
            }
        }

        updateTabIndicators(0) // Starting position
    }

    @SuppressLint("ResourceType")
    private fun updateTabIndicators(position: Int) {
        for (i in 0 until myPagerAdapter.count) {
            val tab = binding.myTab.getTabAt(i)
            val selected = tab?.customView?.findViewById<ImageView>(R.id.selectedd)
            val unselected = tab?.customView?.findViewById<ImageView>(R.id.unselectedd)

            if (i == position) {
                // Tanlangan tab uchun indikator ko'rinadi va animatsiya qo'llanadi
                selected?.visibility = View.VISIBLE
                unselected?.visibility = View.INVISIBLE

                // Indikatorni sekinroq ko'tarish animatsiyasi
                if (selected != null) {
                    animateTabIndicator(selected)
                }
            } else {
                // Tanlanmagan tab uchun indikator yashirin
                selected?.visibility = View.INVISIBLE
                unselected?.visibility = View.VISIBLE

                // Oldingi tanlangan tabdan pastga animatsiya qo'shish
                if (binding.viewPager.currentItem == position - 1) {
                    animateUnselectedIndicator(unselected)
                }
            }
        }
    }

    private fun animateUnselectedIndicator(view: ImageView?) {
        // Agar view mavjud bo'lsa, animatsiya qo'llash
        view?.translationY = 0f // Boshlang'ich holat: yuqorida
        view?.visibility = View.VISIBLE // Ko'rinadigan holatga keltirish

        // Indikatorni pastga ko'tarish
        val animator = ObjectAnimator.ofFloat(view, "translationY", 30f) // 30f - pastga harakat
        animator.duration = 300 // animatsiya davomiyligi (ms)
        animator.interpolator = AccelerateDecelerateInterpolator() // sekinroq boshlanib va tugatiladi
        animator.start()
    }




    private fun animateTabIndicator(view: ImageView) {
        // Boshlang'ich holat: pastda
        view.translationY = 30f

        // Indikatorni sekinroq yuqoriga ko'tarish
        val animator = ObjectAnimator.ofFloat(view, "translationY", 0f)
        animator.duration = 300 // animatsiya davomiyligi (ms)
        animator.interpolator = AccelerateDecelerateInterpolator() // sekinroq boshlanib va tugatiladi
        animator.start()
    }
}

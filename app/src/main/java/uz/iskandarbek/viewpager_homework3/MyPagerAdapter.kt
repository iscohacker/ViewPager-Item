package uz.iskandarbek.viewpager_homework3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class MyPagerAdapter : PagerAdapter() {
    override fun getCount(): Int = 4

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val itemPager =
            LayoutInflater.from(container.context).inflate(R.layout.item_page, container, false)

        val imageUser = itemPager.findViewById<ImageView>(R.id.imageClick)
        val tv_title = itemPager.findViewById<TextView>(R.id.tv_title)
        val tv_message = itemPager.findViewById<TextView>(R.id.tv_message)

        when (position) {
            0 -> {
                imageUser.setImageResource(R.drawable.user1)
                tv_title.text = "Geoaxborot"
                tv_message.text =
                    "Geoaxborot moduli orqali siz o'zingizga yaqin bo'lgan migrantlar va boshqa muassasalar haqida ma'lumot olishingiz mumkin"
            }

            1 -> {
                imageUser.setImageResource(R.drawable.user2)
                tv_title.text = "Hquqiy axborot"
                tv_message.text =
                    "Huquqiy axborot moduli orqali siz migratsiya sohasiga oid yangiliklardan, qonunlardan xabardor bo'lishingiz va online konsultatsiya olishingiz mumkin"
            }

            2 -> {
                imageUser.setImageResource(R.drawable.user3)
                tv_title.text = "To'loz xizmatlari"
                tv_message.text =
                    "To'lov xizmatlari moduli orqali siz masofadan turib turli xil xizmatlarga to'lovlar haqida ma'lumot olishingiz va to'lovlarni amalga oshirishingiz mumkin"
            }

            3 -> {
                imageUser.setImageResource(R.drawable.user4)
                tv_title.text = "Qo'shimcha imkoniyatlar"
                tv_message.text =
                    "Qo'shimcha imkoniyatlar moduli orqali siz interaktiv so'zlashgichlar va lug'atlar yordamida til bilish savodxonligingizni oshirishingiz mumkin"
            }
        }
        container.addView(itemPager)

        return itemPager
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}
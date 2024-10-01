package uz.iskandarbek.viewpager_homework3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uz.iskandarbek.viewpager_homework3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var myPagerAdapter: MyPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        myPagerAdapter = MyPagerAdapter()
        binding.viewPager.adapter = myPagerAdapter


        binding.next.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem < myPagerAdapter.count - 1) {
                binding.viewPager.currentItem = currentItem + 1
            }
        }
    }
}
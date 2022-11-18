package uz.muhammadziyo.fullquestionofsports

import android.app.AlertDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.muhammadziyo.fullquestionofsports.databinding.FragmentTennisBinding
import uz.muhammadziyo.fullquestionofsports.databinding.ItemChekDialogBinding
import uz.muhammadziyo.fullquestionofsports.databinding.ItemDialogBinding
import uz.muhammadziyo.fullquestionofsports.models.MyData
import uz.muhammadziyo.fullquestionofsports.models.Question
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FragmentTennis : Fragment() {
    private lateinit var binding: FragmentTennisBinding
    private lateinit var list: ArrayList<Question>
    private var position: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTennisBinding.inflate(layoutInflater)

        Handler().postDelayed(object : Runnable {
            override fun run() {
                binding.splash.visibility = View.INVISIBLE
            }
        }, 3000)
        loadList()
        loadQuestion()

        binding.answerA.setOnClickListener {
            chekQuestion(1)
        }

        binding.answerB.setOnClickListener {
            chekQuestion(2)
        }



        return binding.root
    }


    private fun loadQuestion() {
        binding.question.text = list[position].question
        binding.txtAnswer.text = list[position].answer1
        binding.txtAnswerB.text = list[position].answer2
    }

    private fun loadList() {
        list = ArrayList()

        list.add(Question("What is the name of the four most important tennis tournaments in the world?",
            "Grand Slam",
            "World Series",
            1
        ))

        list.add(Question("Which Grand Slam tournament is annually held in January?",
            "The French Open",
            "The Australian Open",
            2
        ))

        list.add(Question("Which Russian tennis player was the first one win a Grand Slam title in singles in 1996?",
            "Yevgeny Aleksandrovich Kafelnikov",
            "Maria Yuryevna Sharapova",
            1
        ))

        list.add(Question("What is it called when the score is 40 – 40?",
            "Equal",
            "Deuce",
            2
        ))

        list.add(Question("Which male American tennis player was the first one to win 3 straight titles at Wimbledon?",
            "Andy Roddick",
            "Peter Sampras",
            2
        ))

        list.add(Question("Which tennis player says “Express yourself” in a camera commercial?",
            "Andre Agassi",
            "Roger Federer",
            1
        ))

        list.add(Question("What does an ace mean?",
            "A trick shot",
            "A ball not returned by the opponent",
            2
        ))

        list.add(Question("The Wimbledon Championships began in which year?",
            "1877",
            "1905",
            1
        ))

        list.add(Question("The French Open tournament is played on which surface?",
            "Clay",
            "Grass",
            1

        ))

        list.add(Question("Which tennis player won the Men’s Singles at 2017 Wimbledon?",
            "Novak Djokovic",
            "Roger Federer",
            2
        ))

    }


    private fun chekQuestion(answer: Int) {

        if (answer == list[position].correctAnswer) {
            if (list.size == position + 1) {
                MyData.correct += 1

                position = 0
                chekDialog(1)
                loadQuestion()
                alertDialog()
            } else {
                position += 1
                MyData.correct += 1
                chekDialog(1)
                loadQuestion()
            }

        } else {
            if (list.size == position + 1) {
                MyData.incorrect += 1
                position = 0
                chekDialog(2)
                loadQuestion()
                alertDialog()
            } else {
                MyData.incorrect += 1
                chekDialog(2)
                position += 1
                loadQuestion()

            }

        }

    }

    private fun chekDialog(answer: Int) {
        val dialog = BottomSheetDialog(binding.root.context, R.style.NewDialog)
        val itemDialog = ItemChekDialogBinding.inflate(layoutInflater)
        if (answer == 1) {
            itemDialog.chek.text = "Correct!"
            itemDialog.chek.setTextColor(Color.GREEN)
            dialog.setContentView(itemDialog.root)
            dialog.show()
            Handler().postDelayed(object : Runnable {
                override fun run() {
                    dialog.dismiss()
                }
            }, 1000)
        } else {
            itemDialog.chek.text = "Wrong!"
            itemDialog.chek.setTextColor(Color.RED)
            dialog.setContentView(itemDialog.root)
            dialog.show()
            Handler().postDelayed(object : Runnable {
                override fun run() {
                    dialog.dismiss()
                }
            }, 1000)
        }

    }

    private fun alertDialog() {
        binding.xira.visibility = View.VISIBLE
        val dialog = AlertDialog.Builder(binding.root.context, R.style.NewDialog).create()
        val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val myDateObj = LocalDateTime.now()
                val myFormatObj: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")

                val formattedDate = myDateObj.format(myFormatObj)

                itemDialogBinding.txtCorrect.text = "correct answer: ${MyData.correct}"
                itemDialogBinding.txtWrong.text = "wrong answer: ${MyData.incorrect}"
                itemDialogBinding.date.text = formattedDate

            }
        }
        dialog.setView(itemDialogBinding.root)
        dialog.setOnDismissListener {
            binding.xira.visibility = View.INVISIBLE
            MyData.correct = 0
            MyData.incorrect = 0
        }
        dialog.show()


    }


}
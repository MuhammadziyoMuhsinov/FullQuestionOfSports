package uz.muhammadziyo.fullquestionofsports

import android.app.AlertDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.muhammadziyo.fullquestionofsports.databinding.FragmentBasketballBinding
import uz.muhammadziyo.fullquestionofsports.databinding.ItemChekDialogBinding
import uz.muhammadziyo.fullquestionofsports.databinding.ItemDialogBinding
import uz.muhammadziyo.fullquestionofsports.models.MyData
import uz.muhammadziyo.fullquestionofsports.models.Question
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FragmentBasketball : Fragment() {

    private lateinit var binding: FragmentBasketballBinding
    private lateinit var list: ArrayList<Question>
    private var position: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBasketballBinding.inflate(layoutInflater)


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

        list.add(Question(
            "What is the highest governing body of basketball?",
            "EuroLeague",
            "FIBA",
            2
        ))

        list.add(Question("When was basketball made?",
            "1737",
            "1891",
            2
        ))

        list.add(Question("Who made basketball?",
            "James Naismith",
            "William Morgan",
            1
        ))

        list.add(Question("A rebound is…",
            "a type of move",
            "a statistic",
            2
        ))

        list.add(Question("Kicking a basketball is what kind of foul?",
            "Personal foul",
            "Technical foul",
            2
        ))

        list.add(Question("Physical contact between two players is what kind of foul?",
            "Personal foul",
            "Flagrant foul",
            1
        ))

        list.add(Question("Which team won the first NBA Championship?",
            "Cavaliers",
            "Lakers",
            2
        ))

        list.add(Question("The first Coach of the Year Award was presented to…",
            "Harry Gallatin",
            "Steve Kerr",
            1
        ))

        list.add(Question("The first NBA player to win five season MVP awards was…",
            "Bill Russell",
            "LeBron James",
            1
        ))

        list.add(Question("How long can a defensive player stand in the paint without guarding anyone?",
            "3 seconds",
            "5 seconds",
            1
        ))




    }

    private fun chekQuestion(answer: Int) {

        if (answer == list[position].correctAnswer) {
            if (list.size == position + 1) {
                MyData.correct += 1

                chekDialog(1)
                position = 0
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
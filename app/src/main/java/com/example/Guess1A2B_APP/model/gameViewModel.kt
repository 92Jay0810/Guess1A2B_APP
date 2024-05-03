package com.example.Guess1A2B_APP.model

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class gameViewModel :ViewModel() {
    //目前的分數
    private var _score = 0
    var score: Int
        get() = _score
        set(value) {
            _score = value
        }
    //目前幾回合
    private var _stage = 0
    var stage: Int
        get() =  _stage
        set(value) {
            _stage= value
        }
    private var _attempt = 0
    var attempt: Int
        get() =  _attempt
        set(value) {
            _attempt= value
        }
    //保留出現過的字，避免重複出現
    private var _wordsList: MutableList<String> = mutableListOf()
    var wordsList:  MutableList<String>
        get() =  _wordsList
        set(value) {
            _wordsList= value
        }
    //保留每個stage底下guess的資料 包含玩家輸入的文字和結果
    private var _guessResult: MutableList<guessResult> = mutableListOf()
    var guessResult:  MutableList<guessResult>
        get() =  _guessResult
        set(value) {
            _guessResult= value
        }
    //保留每個遊戲底下stage的資料 包含每個stage答案 和剩下的attempt 以及list of guess
    private var _stageResult: MutableList<stageResult> = mutableListOf()
    var stageResult:  MutableList<stageResult>
        get() =  _stageResult
        set(value) {
            _stageResult= value
        }
    //gameResult 紀錄game的資訊
    private  var _gameResult:MutableList<gameResult> = mutableListOf()
    var gameResult:  MutableList<gameResult>
        get() =  _gameResult
        set(value) {
            _gameResult= value
        }
    //玩家目前出現的字(正確答案)
    private lateinit var _currentWord: String
    val currentWord: String
        get() = _currentWord
    private fun getNextWord() {
        var tempWord =Random.nextInt(10).toString()
        var tempWordCount=1
        while (tempWordCount<4){
            var tempChr = Random.nextInt(10).toString()
            if(!tempWord.contains(tempChr)){
                //不包含的話就 ++
                tempWordCount++
                tempWord+=tempChr
            }
        }
        //檢查有沒有重複出現過
        if (wordsList.contains(tempWord)) {
            getNextWord()
        } else {
            //更新到目前出現的字
            _currentWord = tempWord
            //增加猜的數字
            wordsList.add(_currentWord)
            Log.d("GameFragment", "Word: ${_currentWord} ")
        }
    }
    private  fun getNextStage(){
        val tempGuessResult = guessResult.toList().toMutableList()
        var temp_stage= stageResult(currentWord,10-attempt,tempGuessResult)
        ++stage
        attempt=10

        //清除時 因為是參考列別 在stageResult中的guessResult也會清除 存不到 要記得用備份
        guessResult.clear()
        getNextWord()
        stageResult.add(temp_stage)
    }
    private  fun finalresult(){
        val tempStageResult = stageResult.toList().toMutableList()
        var templatesResult= gameResult(score,tempStageResult)
        score = 0
        stage = 1
        wordsList.clear()
        //清除時 因為是參考列別 在gameResult中的stageResult也會清除 存不到 要記得用copy
        stageResult.clear()
        getNextWord()
        gameResult.add(templatesResult)
    }
    init{
        ++stage
        attempt=10
        getNextWord()
        Log.d("GameFragment","ViewModel created")
    }
    //要檢查1A2B
    fun isUserWordCorrect(playerWord: String): String {
        if (playerWord.equals(_currentWord, true)) {
            //正確就加分
            score+=25
            --attempt
            return "4A0B"
        }
        var countA =0
        var countB =0
        for ( i in currentWord.indices){
            for (j in playerWord.indices){
                if(currentWord[i]==playerWord[j]){
                    if(i==j){
                        countA++
                    }else{
                        countB++
                    }
                }
            }
        }
        --attempt
        return countA.toString()+"A"+countB.toString()+"B"
    }

    fun nextStage(){
        return getNextStage()
    }
    fun reinitializeData() {
        finalresult()
    }
    private var _selectgame = 0
    var selectgame: Int
        get() =  _selectgame
        set(value) {
            _selectgame= value
        }
    private var _selectstage = 0
    var selectstage: Int
        get() =  _selectstage
        set(value) {
            _selectstage= value
        }

}
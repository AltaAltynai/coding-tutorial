class QA(val daysLeft: Int, name: String, department: String) : Engineer(name, department) {
    fun releaseTesting(features: ArrayList<String>) : Boolean {
        return features.size / 2 <= daysLeft
    }
}

/*
check if daysLeft is empty? // "error"
check releaseTesting type // <Boolean>
check daysLeft => features.size/2 //true
check daysLeft = 0 // false
check daysLeft < features.size // false
check daysLeft < 0 // false
check features.size <= 0 // true
 */
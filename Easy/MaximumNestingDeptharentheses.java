// 1614. Maximum Nesting Depth of the Parentheses


public class MaximumNestingDeptharentheses {
    public int maxDepth(String s) {
        int maxDep = 0 ;
        int currDep = 0 ;

        for(char ch : s.toCharArray()){
            if(ch == '('){
                currDep++;
                maxDep = Math.max(maxDep , currDep ) ;
            }else if(ch == ')'){
                currDep--;
            }
        }
        return maxDep ; 
    }
}

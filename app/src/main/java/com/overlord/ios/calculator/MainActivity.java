package com.overlord.ios.calculator;


import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton fncButton;
    ImageButton[] fnc = new ImageButton[2];
    LinearLayout[] layouts = new LinearLayout[2];
    LinearLayout spacer;
    List<View> btns = new ArrayList<>();
    TextView exp, res;
    String op;
    Boolean isBasic, isScientific;

    private List<View> getbuttons(LinearLayout layout) {
        List<View> views = new ArrayList<>();

        for (int i = 0; i < layout.getChildCount(); i++) {
            View l1 = layout.getChildAt(i);

            if (l1 instanceof Button)
                views.add(l1);
            else if (l1 instanceof LinearLayout) {
                views.addAll(getbuttons((LinearLayout) l1));
            }
        }
        return views;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Exp", exp.getText().toString());
        outState.putString("Res", res.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fncButton = findViewById(R.id.functions);
        fnc[0] = findViewById(R.id.functions2);
        fnc[1] = findViewById(R.id.functions3);
        layouts[0] = findViewById(R.id.special);
        layouts[1] = findViewById(R.id.normal);
        exp = findViewById(R.id.expression);
        res = findViewById(R.id.result);
        op = "";
        isBasic = true;
        isScientific = false;

        if (savedInstanceState != null) {
            exp.setText(savedInstanceState.getString("Exp", ""));
            res.setText(savedInstanceState.getString("Res", ""));
        }

        for (LinearLayout l1 : layouts)
            btns.addAll(getbuttons(l1));

        fncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, fncButton);
                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
                spacer = findViewById(R.id.spacer);

                LinearLayout.LayoutParams spacerParams = (LinearLayout.LayoutParams) spacer.getLayoutParams();
                LinearLayout.LayoutParams specialParams = (LinearLayout.LayoutParams) layouts[0].getLayoutParams();


                popup.setOnMenuItemClickListener(item -> {
                    int id = item.getItemId();

                    if (id == R.id.basic && !isBasic) {
                        ValueAnimator animator = ValueAnimator.ofFloat(0.10f, 0.19f);
                        ValueAnimator animator2 = ValueAnimator.ofFloat(0.4f, 0.0f);

                        animator.setDuration(350);
                        animator2.setDuration(350);

                        animator.addUpdateListener(animation -> {
                            spacerParams.weight = (Float) animation.getAnimatedValue();
                            spacer.setLayoutParams(spacerParams);
                        });

                        animator2.addUpdateListener(animation -> {
                            specialParams.weight = (Float) animation.getAnimatedValue();
                            layouts[0].setLayoutParams(specialParams);
                        });

                        animator.start();
                        animator2.start();

                        isBasic = true;
                        isScientific = false;
                        return true;
                    } else if (id == R.id.scientific && !isScientific) {
                        ValueAnimator animator = ValueAnimator.ofFloat(0.19f, 0.10f);
                        ValueAnimator animator2 = ValueAnimator.ofFloat(0.0f, 0.4f);

                        animator.setDuration(350);
                        animator2.setDuration(350);

                        animator.addUpdateListener(animation -> {
                            spacerParams.weight = (Float) animation.getAnimatedValue();
                            spacer.setLayoutParams(spacerParams);
                        });

                        animator2.addUpdateListener(animation -> {
                            specialParams.weight = (Float) animation.getAnimatedValue();
                            layouts[0].setLayoutParams(specialParams);
                        });

                        animator.start();
                        animator2.start();

                        isScientific = true;
                        isBasic = false;
                        return true;
                    }
                    return false;
                });
                popup.show();
            }
        });

        for (ImageButton btn : fnc)
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(MainActivity.this, btn);
                    popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

                    LinearLayout.LayoutParams specialParams = (LinearLayout.LayoutParams) layouts[0].getLayoutParams();
                    LinearLayout.LayoutParams normalParams = (LinearLayout.LayoutParams) layouts[1].getLayoutParams();


                    popup.setOnMenuItemClickListener(item -> {
                        int id = item.getItemId();

                        if (id == R.id.basic && !isBasic) {
                            ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 0.5f);
                            ValueAnimator animator2 = ValueAnimator.ofFloat(0.5f, 0.0f);

                            animator.setDuration(350);
                            animator2.setDuration(350);

                            animator.addUpdateListener(animation -> {
                                normalParams.weight = (Float) animation.getAnimatedValue();
                                layouts[1].setLayoutParams(normalParams);
                            });

                            animator2.addUpdateListener(animation -> {
                                specialParams.weight = (Float) animation.getAnimatedValue();
                                layouts[0].setLayoutParams(specialParams);
                            });

                            animator.start();
                            animator2.start();

                            isBasic = true;
                            isScientific = false;
                            return true;
                        } else if (id == R.id.scientific && !isScientific) {
                            ValueAnimator animator = ValueAnimator.ofFloat(0.5f, 0.0f);
                            ValueAnimator animator2 = ValueAnimator.ofFloat(0.0f, 0.5f);

                            animator.setDuration(350);
                            animator2.setDuration(350);

                            animator.addUpdateListener(animation -> {
                                normalParams.weight = (Float) animation.getAnimatedValue();
                                layouts[1].setLayoutParams(normalParams);
                            });

                            animator2.addUpdateListener(animation -> {
                                specialParams.weight = (Float) animation.getAnimatedValue();
                                layouts[0].setLayoutParams(specialParams);
                            });

                            animator.start();
                            animator2.start();

                            isScientific = true;
                            isBasic = false;
                            return true;
                        }
                        return false;
                    });
                    popup.show();
                }
            });


        for (View btn : btns) {
            if (((Button) btn).getText().equals("AC"))
                ((Button) btn).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        res.setText("0");
                        exp.setText("");
                        op = "";
                        return true;
                    }
                });

            ((Button) btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (res.getText().equals("Div By 0"))
                        res.setText("0");

                    String btnText = ((Button) btn).getText().toString();

                    switch (btnText) {
                        case "=":
                            try {
                                String expression = res.getText().toString();
                                expression = preprocessExpression(expression);
                                double result = evalExpression(expression);
                                res.setText(Double.toString(result));
                                exp.setText(expression);
                                op = "";
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            break;

                        case "AC":
                            if (isOperator(res.getText().charAt(res.length() - 1)))
                                op = "";
                            res.setText(res.getText().subSequence(0, res.length() - 1));
                            if (res.length() == 0 || exp.length() > 0) {
                                res.setText("0");
                                exp.setText("");
                            }
                            break;

                        case "+":
                        case "-":
                        case "%":
                        case "x":
                        case "÷":
                            if (isOperator(res.getText().charAt(res.length() - 1)))
                                res.setText(res.getText().subSequence(0, res.length() - 1));
                            res.setText(res.getText().toString() + btnText);
                            exp.setText("");
                            break;

                        case "sin":
                        case "sinh":
                        case "cos":
                        case "cosh":
                        case "tan":
                        case "tanh":
                        case "ln":
                        case "log₁₀":
                            if (res.getText().equals("0"))
                                res.setText(btnText + "(");
                            else if (isOperator(res.getText().charAt(res.length() - 1)))
                                res.setText(res.getText().toString() + btnText + "(");
                            else
                                res.setText(res.getText().toString() + "x" + btnText + "(");
                            break;
                        case "x!":
                            res.setText(res.getText() + "!");
                            break;
                        case "xʸ":
                            res.setText(res.getText() + "^");
                            break;
                        case "²√x":
                            if (res.getText().equals("0")) {
                                res.setText("√");
                                return;
                            }
                            if (res.length() > 0) {
                                if (!isOperator(res.getText().charAt(res.length() - 1)))
                                    res.setText(res.getText() + "x√");
                                else
                                    res.setText(res.getText() + "√");
                            }
                            break;
                        case "Rand":
                        case "Rad":
                        case "EE":
                        case "ʸ√x":
                        case "³√x":
                        case "¹⁄ₓ":
                        case "10ˣ":
                        case "2ⁿᵈ":
                        case "+/-":
                        case "mr":
                        case "m-":
                        case "m+":
                        case "mc":
                            break;
                        case "x²":
                            res.setText(res.getText() + "^2");
                            break;
                        case "x³":
                            res.setText(res.getText() + "^3");
                            break;
                        case "\uD835\uDC52ˣ":
                            if (res.getText().equals("0"))
                                res.setText("e^");
                            else if (!res.getText().equals(""))
                                if (!isOperator(res.getText().charAt(res.length() - 1)) && !(res.getText().charAt(res.length() - 1) == '√') && !(res.getText().charAt(res.length() - 1) == '^') && !(res.getText().charAt(res.length() - 1) == '('))
                                    res.setText(res.getText() + "xe^");
                                else
                                    res.setText(res.getText() + "e^");
                            break;
                        case "\uD835\uDC52":
                            if (res.getText().equals("0"))
                                res.setText("e^1");
                            else if (!res.getText().equals(""))
                                if (!isOperator(res.getText().charAt(res.length() - 1)) && !(res.getText().charAt(res.length() - 1) == '√') && !(res.getText().charAt(res.length() - 1) == '^') && !(res.getText().charAt(res.length() - 1) == '('))
                                    res.setText(res.getText() + "xe^1");
                                else
                                    res.setText(res.getText() + "e^1");
                            break;
                        case "π":
                            if (res.getText().equals("0"))
                                res.setText("3.14");
                            else if (!res.getText().equals(""))
                                if (!isOperator(res.getText().charAt(res.length() - 1)) && !(res.getText().charAt(res.length() - 1) == '√') && !(res.getText().charAt(res.length() - 1) == '^')  && !(res.getText().charAt(res.length() - 1) == '('))
                                    res.setText(res.getText() + "x3.14");
                                else
                                    res.setText(res.getText() + "3.14");
                            break;
                        default:
                            if (exp.length() > 0 || res.getText().equals("0")) {
                                exp.setText("");
                                res.setText("");
                            }
                            res.setText(res.getText().toString() + btnText);
                    }
                }
            });
        }
    }

    private Boolean isOperator(char t) {
        return t == '+' || t == '-' || t == '%' || t == 'x' || t == '÷';
    }

    private double factorial(int n) {
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private String preprocessExpression(String expr) {
        while (expr.contains("ln(")) {
            int start = expr.indexOf("ln(");
            int open = start + 2;
            int close = expr.indexOf(")", open);
            if (close == -1) throw new IllegalArgumentException("Invalid ln syntax");
            String inside = expr.substring(open + 1, close);
            double val = evalExpression(preprocessExpression(inside));
            if (val <= 0) throw new ArithmeticException("ln undefined for ≤ 0");
            double lnResult = Math.log(val);
            expr = expr.substring(0, start) + lnResult + expr.substring(close + 1);
        }

        while (expr.contains("log₁₀(")) {
            int start = expr.indexOf("log₁₀(");
            int open = start + 5;
            int close = expr.indexOf(")", open);
            if (close == -1) throw new IllegalArgumentException("Invalid log₁₀ syntax");

            String inside = expr.substring(open + 1, close);
            double val = evalExpression(preprocessExpression(inside));
            if (val <= 0) throw new ArithmeticException("log₁₀ undefined for ≤ 0");
            double logResult = Math.log10(val);

            expr = expr.substring(0, start) + logResult + expr.substring(close + 1);
        }

        while (expr.contains("e^")) {
            int i = expr.indexOf("e^");

            int start = i + 2;
            int end = start;
            while (end < expr.length() && (Character.isDigit(expr.charAt(end)) || expr.charAt(end) == '.'))
                end++;
            String expStr = expr.substring(start, end);
            double exponent = evalExpression(preprocessExpression(expStr));
            double result = Math.exp(exponent);

            expr = expr.substring(0, i) + result + expr.substring(end);
        }

        while (expr.contains("sin") || expr.contains("cos") || expr.contains("tan")) {
            int start = -1;
            String func = "";

            for (String f : new String[]{"sinh", "cosh", "tanh", "sin", "cos", "tan"}) {
                int idx = expr.indexOf(f);
                if (idx != -1 && (start == -1 || idx < start)) {
                    start = idx;
                    func = f;
                }
            }

            if (start == -1) break;
            int open = expr.indexOf("(", start);
            int close = expr.indexOf(")", open);
            if (open == -1 || close == -1)
                throw new IllegalArgumentException("Invalid trig syntax");

            String inside = expr.substring(open + 1, close);
            double val = evalExpression(inside);
            double radians = Math.toRadians(val);
            double trigResult;

            switch (func) {
                case "sin":
                    trigResult = Math.sin(radians);
                    break;
                case "cos":
                    trigResult = Math.cos(radians);
                    break;
                case "tan":
                    if ((val % 180) == 90) {
                        throw new ArithmeticException("Undefined Result of tan" + val);
                    }
                    trigResult = Math.tan(radians);
                    break;
                case "sinh":
                    trigResult = Math.sinh(radians);
                    break;
                case "cosh":
                    trigResult = Math.cosh(radians);
                    break;
                case "tanh":
                    trigResult = Math.tanh(radians);
                    break;
                default:
                    trigResult = 0;
            }
            if (Math.abs(trigResult) < 1e-10) trigResult = 0;
            expr = expr.substring(0, start) + trigResult + expr.substring(close + 1);
        }

        while (expr.contains("!")) {
            int idx = expr.indexOf("!");
            int start = idx - 1;
            while (start >= 0 && (Character.isDigit(expr.charAt(start)) || expr.charAt(start) == '.')) {
                start--;
            }
            start++;
            String numberStr = expr.substring(start, idx);
            double number = Double.parseDouble(numberStr);

            if (number < 0 || number != Math.floor(number)) {
                throw new ArithmeticException("Factorial only defined for non-negative integers");
            }

            double fact = factorial((int) number);
            expr = expr.substring(0, start) + fact + expr.substring(idx + 1);
        }


        while (expr.contains("^")) {
            int i = expr.indexOf("^");

            int l = i - 1;
            while (l >= 0 && (Character.isDigit(expr.charAt(l)) || expr.charAt(l) == '.')) l--;
            double base = Double.parseDouble(expr.substring(l + 1, i));

            int r = i + 1;
            while (r < expr.length() && (Character.isDigit(expr.charAt(r)) || expr.charAt(r) == '.'))
                r++;
            double pow = Double.parseDouble(expr.substring(i + 1, r));

            double result = Math.pow(base, pow);
            expr = expr.substring(0, l + 1) + result + expr.substring(r);
        }

        while (expr.contains("√")) {
            int i = expr.indexOf("√");
            int r = i + 1;
            while (r < expr.length() && (Character.isDigit(expr.charAt(r)) || expr.charAt(r) == '.'))
                r++;
            double val = Double.parseDouble(expr.substring(i + 1, r));
            double result = Math.sqrt(val);
            expr = expr.substring(0, i) + result + expr.substring(r);
        }

        return expr;
    }

    private double evalExpression(String expr) {
        expr = expr.replace("÷", "/").replace("x", "*");
        expr = expr.replaceAll("--", "+");

        ArrayList<Double> nums = new ArrayList<>();
        ArrayList<Character> ops = new ArrayList<>();

        StringBuilder num = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                num.append(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%') {
                if (num.length() == 0 && c == '-') {
                    num.append('-');
                    continue;
                }
                nums.add(Double.parseDouble(num.toString()));
                num.setLength(0);
                ops.add(c);
            }
        }
        if (num.length() > 0)
            nums.add(Double.parseDouble(num.toString()));

        for (int i = 0; i < ops.size(); i++) {
            char op = ops.get(i);
            if (op == '*' || op == '/' || op == '%') {
                double a = nums.get(i);
                double b = nums.get(i + 1);
                double r = 0;
                switch (op) {
                    case '*':
                        r = a * b;
                        break;
                    case '/':
                        if (b == 0) throw new ArithmeticException("Div By 0");
                        r = a / b;
                        break;
                    case '%':
                        r = a % b;
                        break;
                }
                nums.set(i, r);
                nums.remove(i + 1);
                ops.remove(i--);
            }
        }

        double result = nums.get(0);
        for (int i = 0; i < ops.size(); i++) {
            char op = ops.get(i);
            double b = nums.get(i + 1);
            if (op == '+') result += b;
            else if (op == '-') result -= b;
        }

        return result;
    }
}

package edu.project2;



public class Cell {
        private boolean isWall;
        private boolean isPath;

        public Cell() {
            this.isWall = true;
            this.isPath = false;
        }

        public boolean isWall() {
            return isWall;
        }

        public boolean isClear() {
            return !isWall;
        }

        public void makeWall() {
            isWall = true;
        }

        public void makeClear() {
            isWall = false;
        }

        public void makePath() {
            if (!isWall()) {
                isPath = true;
            }
        }

        public void delPath() {
            isPath = false;
        }

        public boolean isPath() {
            return isPath;
        }

    }



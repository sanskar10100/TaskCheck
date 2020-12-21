# TaskCheck
Android Native app for daily task management with a minimalistic material design approach.

## Features:
- Minimalistic UI. Less interaction, more efficiency.
- View all tasks in a compact yet comprehensive list.
- Add multiple additional attributes to each task, like due date, due time and priority level.
- Sort tasks by due date or priority.
- Dark Theme support for reduced eye straing and better battery conservation.
- Long tap on a task to mark it as complete.
- Option to switch to Hindi by adjusting system settings.
- Clear all tasks by tapping a single button.

## Key Technical Components:
- ConstraintLayout for proper positioning.
- CardView with nested ConstraintLayout in task addition section for emphasis.
- RecyclerView for displaying the scrollable task list with on-demand binding.
- Room persistance library at two endpoints, launcha and closure for fetching and saving, respectively.
- DatePicker and TimePicker fragments for date and time selection.
- Popup menus for concise option lists.
- Custom AppBar Menu icons for added convenience.
- Snackbar for displaying HOW-TO on launch.

## APK Available for Download in the Releases section
Alternatively, click here to download.

---

Huge shoutout to [Pranjal](https://github.com/pranjal-codes) for providing me with inspiration for building this little app.




1. 主窗口
2. 文件菜单
    - 新建
    - 打开
    - 关闭
    - 保存
    - 另存为
    - 设置
    - 退出
3. 编辑
   - 撤销
   - 重做
   - 剪切
   - 复制
   - 粘贴
   - 删除
   - 全选
   - 取消全选
4. 查找
   - 查找
   - 替换

类如下


NoteapdApp : 程序入口，加载主窗口
NotepadController : 负责主窗口，以及主窗口控件操作的绑定
   成员变量 Settings : 配置类的单例
   settingsController : 设置窗口控制器
   searchManager : 查找窗口控制器
   saveManager : 保存管理器
file/
   FileAccess : 负责具体的文件操作
   SaveManager : 负责记事本打开文件的保存，自动保存机制和退出保存机制，未保存警告等功能
      hasSaved :  是否保存的字段
      boundedFile : 绑定的文件对象，如果没有则为null，但仍然会有一个临时的文件用于自动保存
      saveToFile() : 保存到文件
      saveAs(): 另存为
      save(): 保存到绑定的文件，如果没有绑定文件，则调用saveAs()
      autoSave(): 自动保存，如果没有绑定文件，则保存到临时文件
settings/
   Settings : 负责保有设置字段，单例模式
   SettingsController : 负责设置窗口的具体展示和操作等，同时也附带有负责设置中常规设置的管理
      openSettings() : 打开设置窗口
      closeSettings() : 关闭设置窗口时触发
      applySettings() : 应用设置（保存设置到Settings类），并且重新加载记事本或者其他让新设置立刻生效的方案
      askforSave() : 询问是否保存，如果保存，则applySettings()，仅当有修改时
   AppearanceSettings : 负责设置中外观的设置的管理（包括字体，颜色，字大小），主要是应用
query/
   SearchManager : 负责记事本的搜索功能，包括搜索，替换，高亮等功能
   Searchontroller : 负责搜索窗口的具体展示和操作绑定

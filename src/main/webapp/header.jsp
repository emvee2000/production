<header class="header">
    <nav class="navbar">
        <ul class="navbar-list">
            <li class="navbar-item ${param.page eq "Home"? "here":""}">
                <a href="page" class="navbar-link">Home</a>
            </li>
            <li class="navbar-item ${param.page eq "BPO"? "here":""}">
                <a href="page">BPO's</a>
            </li>
            <li class="navbar-item ${param.page eq "History"? "here":""}">
                <a href="page">History</a>
            </li>
            <li class="navbar-item ${param.page eq "TODO"? "here":""}">
                <a href="page">ToDo</a>
            </li>
        </ul>
    </nav>
</header>

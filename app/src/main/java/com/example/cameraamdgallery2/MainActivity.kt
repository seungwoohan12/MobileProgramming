package com.example.cameraamdgallery2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.cameraamdgallery2.databinding.ActivityMainBinding
import java.io.File
// MainActivity 클래스를 정의하며 __________를 상속받습니다.
// ____________는 안드로이드의 다양한 최신 기능을 사용할 수 있게 해주는 호환성 클래스입니다.
class MainActivity : AppCompatActivity() {
    // '________' 프로퍼티를 ______ 초기화로 정의합니다.
    // ______ 초기화는 해당 프로퍼티가 처음으로 접근될 때 초기화되며, 그 이후에는 초기화된 값을 재사용합니다.
    // 이는 성능을 향상시키고, 불필요한 초기화를 방지하는 데 유용합니다.
    val binding by lazy {
        // __________ 클래스를 _________ 메소드로 인스턴스화합니다.
        // __________ 클래스는 activity_main.xml 레이아웃 파일을 바탕으로
        // 자동으로 생성된 뷰 바인딩 클래스입니다.
        // ________는 XML 레이아웃 파일을 실제 뷰 객체로 변환하는 데 사용됩니다.
        ActivityMainBinding.inflate(layoutInflater)
    }

    // 권한 요청을 처리하기 위한 ___________ 변수를 선언합니다.
    // ________ 키워드는 해당 변수가 나중에 초기화될 것임을 나타내며, 초기화되기 전까지 null일 수 없도록 보장합니다.
    // 카메라 권한 요청을 처리할 _____________________ 변수입니다.
    // 이는 사용자가 카메라 사용 권한을 허용하거나 거부했을 때 호출됩니다
    lateinit var cameraPermission: ActivityResultLauncher<String>
    // 저장소 권한 요청을 처리할 _________________ 변수입니다.
    // 이는 사용자가 저장소 접근 권한을 허용하거나 거부했을 때 호출됩니다.
    lateinit var storagePermission: ActivityResultLauncher<String>
    // ____에 접속할 수 있게 권한을 주기 위해 변수 선언
    // 카메라 앱을 실행하고 결과를 받을 _______________ 변수입니다.
    // 이는 카메라로 촬영한 사진의 ____를 통해 결과를 반환받습니다.
    lateinit var cameraLauncher: ActivityResultLauncher<Uri>
    // 촬영된 사진의 ___를 저장할 변수입니다.
    // 이는 나중에 사진을 액세스하거나 표시할 때 사용됩니다.
    // ___ 변수 선언 -> ___ 는 nullable 이면 안됨
    lateinit var photoUri:Uri

    // ________ 메소드는 액티비티가 처음 생성될 때 호출되는 생명주기 메소드입니다.
    override fun onCreate(savedInstanceState: Bundle?) {
        // 먼저 상위 클래스의 ___________메소드를 호출하여 기본 초기화를 수행합니다.
        super.onCreate(savedInstanceState)
        // setContentView 메소드를 사용하여 액티비티의 레이아웃을 설정합니다.
        // _______는 루트 뷰를 참조하며, 이는 인플레이트된 레이아웃의 최상위 뷰입니다
        setContentView(binding.root)
        // 앱 실행시 앱을 사용하는 동안 권한에 관한 팝업을 띄워줌
        // 저장소 권한 요청을 초기화합니다.
        // __________________________은 권한 요청 계약을 나타냅니다.
        // 이 계약을 사용하여 권한 요청을 시작하고 결과를 처리합니다.
        storagePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            // it -> 결과 (boolean)
            // isGranted는 권한이 허용되었는지 여부를 나타내는 Boolean 값입니다.
            if (it) {
                // 권한이 허용되었을 때 호출됩니다.
                setViews() // 뷰를 설정하는 메소드를 호출합니다.
            } else {
                // 권한이 거부되었을 때 호출됩니다.
                Toast.makeText(baseContext,
                    "외부 저장소 권한을 승인 해야 앱을 사용할 수 있습니다",
                    Toast.LENGTH_SHORT
                ).show()
                finish()  // 권한이 없으면 앱을 종료합니다.
            }
        }
        // 카메라 권한 요청을 초기화합니다.
        // _____________ 메소드를 사용하여 ____________를 초기화합니다.
        // 앱 실행시 앱을 사용하는 동안 카메라 권한에 관한 팝업을 띄움
        cameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            // isGranted는 권한이 허용되었는지 여부를 나타내는 Boolean 값입니다.
            if(it) {
                // 권한이 허용되었을 때 호출됩니다.
                openCamera() // 카메라를 여는 메소드를 호출합니다.
            } else {
                // 권한이 거부되었을 때 호출됩니다.
                Toast.makeText(baseContext,
                    "카메라 권한을 승인 해야 카메라를 사용할 수 있습니다",
                    Toast.LENGTH_SHORT
                ).show()
                finish() // 권한이 없으면 앱을 종료합니다.
            }
        }
        // 카메라 앱을 실행하고 결과를 받을 ActivityResultLauncher를 초기화합니다.
        // it 대신 다른 operator 를 사용 해도 됨 대신 operator -> 로 작성
        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            // isSuccess는 사진 촬영이 성공적으로 완료되었는지 여부를 나타내는 Boolean 값입니다.
            if (isSuccess) {
                // 성공 시 촬영한 사진을 이미지 뷰에 설정합니다
                binding.imageView.setImageURI(photoUri)
            }
        }

        // 저장소 권한을 요청합니다.
        // 권한 요청은 비동기적으로 수행되며, 사용자가 권한을 허용하거나 거부할 때 콜백이 호출됩니다.
        storagePermission.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
    // 뷰를 설정하는 메소드입니다.
    fun setViews() {
        // 버튼을 눌렀을 때 permission 을 launch 해줌
        // 카메라 버튼 클릭 리스너를 설정합니다.
        binding.btnCamera.setOnClickListener {
            cameraPermission.launch(android.Manifest.permission.CAMERA)
        }
    }

    // 카메라를 여는 메소드입니다.
    fun openCamera() {
        // 임시 파일을 생성합니다.
        // ____________ 메소드는 임시 파일을 생성하고 파일 객체를 반환합니다
        // prefix -> createTempFile 에서 뒤에 숫자를 붙여줌
        // suffix -> 확장자
        var photoFile = File.createTempFile(
            "IMG_", // 파일 이름 접두사
            ".jpg", // 파일 확장자
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)  // 파일 저장 경로
        )
        // 생성된 파일의 URI를 가져옵니다.
        // ______________ 메소드는 파일의 URI를 생성하고 반환합니다.
        // file 의 uri 를 가져 와서 사용 -> File 이름과 구조를 Uri 로 바꿔줌
        photoUri = FileProvider.getUriForFile(
            this,
            "${packageName}.provider",
            photoFile
        )
        // 카메라를 실행하여 사진을 촬영합니다.
        // ____________________ 메소드를 사용하여 카메라 앱을 실행하고 사진 촬영을 요청합니다
        cameraLauncher.launch(photoUri)
    }
}